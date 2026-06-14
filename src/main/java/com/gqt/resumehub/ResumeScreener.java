package com.gqt.resumehub;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class ResumeScreener extends JFrame implements ActionListener {

    JLabel lblName, lblEmail, lblPhone, lblSkills, lblQualification, lblExperience;
    JTextField txtName, txtEmail, txtPhone, txtSkills, txtQualification, txtExperience;
    JButton btnSave, btnView, btnClear;
    JTextArea textArea;

    public ResumeScreener() {

        setTitle("Smart Resume Screening and Data Storage System");
        setSize(750, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblName = new JLabel("Name");
        lblName.setBounds(50, 30, 120, 30);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(200, 30, 250, 30);
        add(txtName);

        lblEmail = new JLabel("Email");
        lblEmail.setBounds(50, 80, 120, 30);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(200, 80, 250, 30);
        add(txtEmail);

        lblPhone = new JLabel("Phone");
        lblPhone.setBounds(50, 130, 120, 30);
        add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(200, 130, 250, 30);
        add(txtPhone);

        lblSkills = new JLabel("Skills");
        lblSkills.setBounds(50, 180, 120, 30);
        add(lblSkills);

        txtSkills = new JTextField();
        txtSkills.setBounds(200, 180, 250, 30);
        add(txtSkills);

        lblQualification = new JLabel("Qualification");
        lblQualification.setBounds(50, 230, 120, 30);
        add(lblQualification);

        txtQualification = new JTextField();
        txtQualification.setBounds(200, 230, 250, 30);
        add(txtQualification);

        lblExperience = new JLabel("Experience");
        lblExperience.setBounds(50, 280, 120, 30);
        add(lblExperience);

        txtExperience = new JTextField();
        txtExperience.setBounds(200, 280, 250, 30);
        add(txtExperience);

        btnSave = new JButton("Save");
        btnSave.setBounds(50, 340, 100, 35);
        add(btnSave);

        btnView = new JButton("View");
        btnView.setBounds(180, 340, 100, 35);
        add(btnView);

        btnClear = new JButton("Clear");
        btnClear.setBounds(310, 340, 100, 35);
        add(btnClear);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 400, 620, 180);
        add(scrollPane);

        btnSave.addActionListener(this);
        btnView.addActionListener(this);
        btnClear.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSave) {

            String name = txtName.getText();
            String email = txtEmail.getText();
            String phone = txtPhone.getText();
            String skills = txtSkills.getText();
            String qualification = txtQualification.getText();
            String experience = txtExperience.getText();

            // Conditions
            if (name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Name and Email are mandatory!");
                return;
            }

            try {

                File file = new File("ResumeData.csv");
                boolean fileExists = file.exists();

                FileWriter fw = new FileWriter(file, true);

                // Header row
                if (!fileExists) {
                    fw.write("Name,Email,Phone,Skills,Qualification,Experience\n");
                }

                fw.write(name + "," +
                        email + "," +
                        phone + "," +
                        skills + "," +
                        qualification + "," +
                        experience + "\n");

                fw.close();

                JOptionPane.showMessageDialog(this,
                        "Data Saved Successfully!");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage());
            }
        }

        if (e.getSource() == btnView) {

            textArea.setText("");

            try {

                BufferedReader br =
                        new BufferedReader(
                                new FileReader("ResumeData.csv"));

                String line;

                // Looping
                while ((line = br.readLine()) != null) {
                    textArea.append(line + "\n");
                }

                br.close();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "No records found.");
            }
        }

        if (e.getSource() == btnClear) {

            txtName.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtSkills.setText("");
            txtQualification.setText("");
            txtExperience.setText("");
            textArea.setText("");
        }
    }

    public static void main(String[] args) {
        new ResumeScreener();
    }
}