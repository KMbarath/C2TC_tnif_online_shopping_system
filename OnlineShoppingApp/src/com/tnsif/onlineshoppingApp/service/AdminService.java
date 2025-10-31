package com.tnsif.onlineshoppingApp.service;

import java.util.ArrayList;
import java.util.List;

import com.tnsif.onlineshoppingApp.entity.Admin;

public class AdminService {
    private List<Admin> admins = new ArrayList<>();

    public void addAdmin(Admin admin) { admins.add(admin); }
    public List<Admin> getAllAdmins() { return admins; }
}
