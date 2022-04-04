/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebadcm.cfind;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dcm4che3.net.ApplicationEntity;
import org.dcm4che3.net.Association;
import org.dcm4che3.net.Connection;
import org.dcm4che3.net.Device;
import org.dcm4che3.net.IncompatibleConnectionException;
import org.dcm4che3.net.pdu.AAssociateRQ;
import org.dcm4che3.net.pdu.PresentationContext;

/**
 *
 * @author MH
 */
public class Cfind {
    // Creating the entities taking part in the association...
ApplicationEntity locAE = new ApplicationEntity();

ApplicationEntity remAE = new ApplicationEntity();
Connection localConn;
Connection remoteConn;
Association assoc ;
AAssociateRQ assocReq ;
    public Cfind() {
        locAE.setAETitle("TESTPACS");
        locAE.setAeInstalled(Boolean.TRUE);
        remAE.setAETitle("TESTMH");
        remAE.setAeInstalled(Boolean.TRUE);
    }
    
    public void localPartConecction (){
        
        // Defining the local part of the connection...
         localConn = new Connection();
        localConn.setHostname("localhost");
        localConn.setPort(4006);
        localConn.setBindAddress("127.0.0.1");
        localConn.setProtocol(Connection.Protocol.DICOM);
        locAE.addConnection(localConn);

    }
    
    
    public void remotePartConnection (){
        // Defining the remote part of the connection...
         remoteConn = new Connection();
        remoteConn.setHostname("127.0.0.1");
        
        remoteConn.setPort(2104);
        remoteConn.setProtocol(Connection.Protocol.DICOM);
        remAE.addConnection(remoteConn);
    }
    
    public void createDeviceForConnection(){
        // Creating a device with an executor for the session...
        Device device = new Device("device");
        device.addConnection(localConn);
        device.addApplicationEntity(locAE);
        ExecutorService exec = Executors.newSingleThreadExecutor();
        device.setExecutor(exec);
    }
    
    public void setAssociationParameters (){
        // Setting the association request parameters for an SRR SOP...
            assocReq = new AAssociateRQ();
           assocReq.setCalledAET(remAE.getAETitle());
           assocReq.setCallingAET(locAE.getAETitle());
           assocReq.setApplicationContext("1.2.840.10008.3.1.1.1");
           assocReq.setImplClassUID("1.2.40.0.13.1.3");
           assocReq.setImplVersionName("dcm4che-5.12.0");
           assocReq.setMaxPDULength(16384);
           assocReq.setMaxOpsInvoked(0);
            assocReq.setMaxOpsPerformed(0);
           assocReq.addPresentationContext(new PresentationContext(
            1, "1.2.840.10008.5.1.4.1.2.2.2", "1.2.840.10008.1.2"));

    }
    
    public void startAssociation() {
    try {
        // Initiating the association...
        assoc = locAE.connect(localConn, remoteConn, assocReq);
    } catch (IOException ex) {
        Logger.getLogger(Cfind.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InterruptedException ex) {
        Logger.getLogger(Cfind.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IncompatibleConnectionException ex) {
        Logger.getLogger(Cfind.class.getName()).log(Level.SEVERE, null, ex);
    } catch (GeneralSecurityException ex) {
        Logger.getLogger(Cfind.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
/*/ Specifying the DICOM attributes for the SRR SOP's IOD...
Attributes atts = new Attributes();
atts.setString(0x00080052, VR.LO, "STUDY"); // on this Level
atts.setString(0x0020000D, VR.UI, // Study Instance UID
 "1.2.826.0.1.[...]");

// Performing a DIMSE C-MOVE query with the SRR SOP...
assoc.cmove("1.2.840.10008.5.1.4.1.2.2.2", 0x0000,
 atts, "1.2.840.10008.1.2", locAE.getAETitle(),
 new DimseRSPHandler(assoc.nextMessageID()) { ...*/


}
