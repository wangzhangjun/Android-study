
package com.example.servicemanager.demo;

interface IServiceManager {
    void addService(String name, IBinder service);
    IBinder getService(String name);
}