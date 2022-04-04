/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.pruebadcm;


import com.mycompany.pruebadcm.cfind.Cfind;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dcm4che3.net.IncompatibleConnectionException;
/**
 *
 * @author MH
 */
public class PruebaDCM {
    


    public static void main(String[] args) {
        
            System.out.println("Hello World!");
            Cfind cfind = new Cfind();
            cfind.localPartConecction();
            cfind.createDeviceForConnection();
            cfind.remotePartConnection();
            cfind.setAssociationParameters();
            cfind.startAssociation();
        
    }
}
