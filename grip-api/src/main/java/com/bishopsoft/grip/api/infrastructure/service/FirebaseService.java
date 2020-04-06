package com.bishopsoft.grip.api.infrastructure.service;

import com.bishopsoft.grip.api.infrastructure.exception.HttpException;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.StorageClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class FirebaseService {

    public FirebaseService() {
        FirebaseApp.initializeApp();
    }

    public void uploadToStorage(String name, InputStream fileStream) {
        Bucket bucket = StorageClient.getInstance().bucket();
        bucket.create(name, fileStream);
    }

    public byte[] getFromStorage(String fileName) {
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob = bucket.get(fileName);
        if(blob == null) {
            throw new HttpException("File doesn't exist in bucket", HttpStatus.BAD_REQUEST);
        }
        return blob.getContent();
    }

    public void delete(String fileName) {
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob = bucket.get(fileName);
        if(blob == null) {
            throw new HttpException("File doesn't exist in bucket", HttpStatus.BAD_REQUEST);
        }
        blob.delete();
    }

}
