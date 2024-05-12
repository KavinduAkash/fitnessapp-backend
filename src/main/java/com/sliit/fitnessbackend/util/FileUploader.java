package com.sliit.fitnessbackend.util;

import com.jcraft.jsch.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

public class FileUploader {

    public static boolean upload(String path, String filename) throws IOException {
        String localFilePath = path;
        String remoteFilePath = "/opt/tomcat/webapps/upload/"+filename;
        String host = "164.92.75.84";
        String username = "root";
        String password = "Admin@123Sliit";

        FileUploader fileUploader = new FileUploader();
        fileUploader.uploadFile(localFilePath, remoteFilePath, host, username, password);
        return true;
    }



    public void uploadFile(String localFilePath, String remoteFilePath, String host, String username, String password) {
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession(username, host, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            channelSftp.put(localFilePath, remoteFilePath);

            channelSftp.disconnect();
            session.disconnect();
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        }
    }
}
