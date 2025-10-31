package service;

import java.util.ArrayList;
import java.util.List;
import entity.Admin;

public class AdminService {
    private List<Admin> admins = new ArrayList<>();

    public void addAdmin(Admin admin) { admins.add(admin); }
    public List<Admin> getAllAdmins() { return admins; }
}
