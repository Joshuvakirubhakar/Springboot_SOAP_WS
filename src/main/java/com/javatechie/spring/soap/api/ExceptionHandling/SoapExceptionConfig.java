package com.javatechie.spring.soap.api.ExceptionHandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.AbstractEndpointExceptionResolver;
import org.springframework.ws.soap.SoapBody;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.SoapMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import java.util.Locale;

/**
 * Translates an instance of {@link OrderNotFoundException} to a SOAP Fault.
 */
@Component
public class SoapExceptionConfig extends AbstractEndpointExceptionResolver {
    private static final Logger log = LoggerFactory.getLogger(SoapExceptionConfig.class);
//    private static final ObjectFactory FACTORY = new ObjectFactory();

    private final Marshaller marshaller;

    /**
     * Prepare the {@link #marshaller} so we can marshall {@link FaultMessage} instances to XML.
     * @throws JAXBException In case the JAX-B setup is incorrect.
     */
    public SoapExceptionConfig() throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(Fault.class);
        this.marshaller = jaxbContext.createMarshaller();
    }

    @Override
    protected boolean resolveExceptionInternal(final MessageContext messageContext,
                                               final Object endpoint,
                                               final Exception exception) {
        if (exception instanceof CustomException) {
            final CustomException onfe = (CustomException) exception;

            final Fault faultMessage = new Fault();
            faultMessage.setFaultmsg1(onfe.getFault().getFaultmsg1());
            faultMessage.setFaultmsg2(onfe.getFault().getFaultmsg2());
            Opstatus opstatus = onfe.getFault().getOpstatus();
            faultMessage.setOpstatus(opstatus);
//            faultMessage.getOpstatus().setDate(onfe.getFault().getOpstatus().getDate());
//            faultMessage.getOpstatus().setStatus(onfe.getFault().getOpstatus().getStatus());

            final SoapMessage response = (SoapMessage) messageContext.getResponse();
            final SoapBody soapBody = response.getSoapBody();

//            final SoapFault soapFault = soapBody.addClientOrSenderFault(
//                    "This is an internal Error",
//                    Locale.ENGLISH);
            final SoapFault soapFault = soapBody.addServerOrReceiverFault(
                    "This is an internal Error",
                    Locale.ENGLISH);

            final SoapFaultDetail faultDetail = soapFault.addFaultDetail();
            final Result result = faultDetail.getResult();

            try {
                marshaller.marshal(faultMessage, result);
                return true; // We have handled the Exception.
            } catch (final JAXBException e) {
                // Mention what went wrong, but don't fallback or something. Spring will take care of this.
                log.error("Could not marshall FaultMessage type", e);
            }
        }

        return false; // We did not handle the Exception. Let's hope somebody else does...
    }
}