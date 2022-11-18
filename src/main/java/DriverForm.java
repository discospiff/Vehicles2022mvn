import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Stack;
import java.util.Vector;

public class DriverForm {
    private JPanel pnlMain;
    private JPanel jplButtonBar;
    private JPanel pnlCenterMain;
    private JPanel pnlInnerNorth;
    private JPanel pnlInnerCenter;
    private JButton btnSave;
    private JButton btnDrive;
    private JTextField txtDistance;
    private JList lstVehicles;
    private JComboBox cmbMakeModel;
    private JTextField txtOdometer;
    private JTextField txtMilesPerGallon;
    private JTextField txtGallonsOfGas;
    private JCheckBox cbxConvertible;
    private JTextField txtDescription;
    private JTextPane textPane1;
    private JLabel lblVIN;
    private JTextField txtVIN;
    private JButton btnSearch;
    private JLabel lblBuyerName;
    private JTextField txtGasAdded;
    private JTextField txtPricePerGallon;
    private JButton btnAddGas;
    private JButton btnHighMpgVehicles;
    private JComboBox comboBox1;
    private JButton btnSaveToFile;
    private JButton btnOpenFile;

    private Vector<Vehicle> allVehicles =  new Vector<>();

    private Vehicle vehicle;

    private boolean existingVehicle = false;
    
    private Buyer buyer;

    private Stack<Gasoline> gasoline;

    private static final Logger logger = LogManager.getLogger("vehicles");

    public DriverForm() {

        gasoline = new Stack<>();

        initializeVehicleTypeComboBox();

        try {
            InventoryReader.createVehicle();
        } catch (Exception e) {
            logger.error(e);
            JOptionPane.showMessageDialog(null, "Unable to read inventory file.");
        }

        BuyerReader.readBuyers();

        lstVehicles.setListData(allVehicles);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strOdometer = txtOdometer.getText();
                int odometer = Integer.parseInt(strOdometer);

                String strMilesPerGallon = txtMilesPerGallon.getText();
                int milesPerGallon = Integer.parseInt(strMilesPerGallon);

                String strGallonsOfGas = txtGallonsOfGas.getText();
                double gallonsOfGas = Double.parseDouble(strGallonsOfGas);

                String type = cmbMakeModel.getSelectedItem().toString();
                if (!existingVehicle) {
                    // create new vehicle, otherwise update existing vehicle.
                    vehicle = Driver.getInstance().createVehicle(type);
                }

                vehicle.setOdometer(odometer);
                vehicle.setGallonsOfGas(gallonsOfGas);
                vehicle.setMilesPerGallon(milesPerGallon);
                String unFilteredString = txtDescription.getText();
                String filteredString = WordValidation.getInstance().filterString(unFilteredString);
                vehicle.setDescription(filteredString);
                txtDescription.setText(filteredString);

                vehicle.setGasoline(gasoline);

                if (cmbMakeModel.getSelectedItem().toString().equals(Driver.MUSTANG)) {
                    if (vehicle instanceof Mustang) {
                        Mustang mustang = (Mustang) vehicle;
                        mustang.setConvertible(cbxConvertible.isSelected());
                    }
                }

                try {
                    BuyerReader.removeBuyer(buyer);
                    vehicle.setBuyer(buyer);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unable to associate buyer with this vehicle.");
                }

                allVehicles.add(vehicle);
                lstVehicles.updateUI();
                existingVehicle = false;
                clearFields();
                gasoline = new Stack<Gasoline>();
            }
        });
        btnDrive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strDistance = txtDistance.getText();
                int distance = Integer.parseInt(strDistance);
                allVehicles.stream().forEach(vehicle -> vehicle.go(distance));
                lstVehicles.updateUI();
            }
        });
        cmbMakeModel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmbMakeModel.getSelectedItem().toString().equals(Driver.MUSTANG)) {
                    cbxConvertible.setEnabled(true);
                } else {
                    cbxConvertible.setEnabled(false);
                    cbxConvertible.setSelected(false);
                }
                buyer = BuyerReader.fetchNextQualifiedBuyer();
                lblBuyerName.setText(buyer.getFirstName() + " " + buyer.getLastName());
            }
        });
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vin = txtVIN.getText();
                vehicle = InventoryReader.fetchVehicle(vin);
                existingVehicle = true;
                txtDescription.setText(vehicle.getDescription());
                txtVIN.setText(vehicle.getVin());
                txtOdometer.setText("" + vehicle.getOdometer());
                txtGallonsOfGas.setText("" + vehicle.getGallonsOfGas());
                txtMilesPerGallon.setText("" + vehicle.getMilesPerGallon());
            }
        });
        btnAddGas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strGasAdded = txtGasAdded.getText();
                double gasAdded = Double.parseDouble(strGasAdded);
                String strPricePerGallon = txtPricePerGallon.getText();
                double pricePerGallon = Double.parseDouble(strPricePerGallon);
                Gasoline gas = new Gasoline();
                gas.setGallons(gasAdded);
                gas.setPrice(pricePerGallon);
                gasoline.push(gas);

            }
        });
        btnHighMpgVehicles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long highMpgVehicles = allVehicles.stream().filter(vehicle -> vehicle.getMilesPerGallon() > 40).count();
                JOptionPane.showMessageDialog(null, "Number of High MPG Vehicles: " + highMpgVehicles);
            }
        });
        btnSaveToFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (FileOutputStream file = new FileOutputStream("vehicles.obj")) {
                    ObjectOutputStream oos = new ObjectOutputStream(file);
                    oos.writeObject(allVehicles);
                    oos.flush();
                    oos.close();
                } catch (FileNotFoundException ex) {
                    logger.error(ex);
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    logger.error(ex);
                    throw new RuntimeException(ex);
                }
            }
        });
        btnOpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (FileInputStream file = new FileInputStream("vehicles.obj")) {
                    ObjectInputStream ois = new ObjectInputStream(file);
                    Vector<Vehicle> inVehicles = (Vector<Vehicle>) ois.readObject();
                    allVehicles.addAll(inVehicles);
                    lstVehicles.updateUI();
                    ois.close();
                } catch (FileNotFoundException ex) {
                    logger.error(ex);
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    logger.error(ex);
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    logger.error(ex);
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void clearFields() {
        txtDescription.setText("");
        txtVIN.setText("");
        txtDistance.setText("");
        txtOdometer.setText("");
        txtGallonsOfGas.setText("");
        txtMilesPerGallon.setText("");
        lblBuyerName.setText("");
        txtGasAdded.setText("");
        txtPricePerGallon.setText("");
    }

    private void initializeVehicleTypeComboBox() {
        DefaultComboBoxModel<String> vehicleTypesModel = new DefaultComboBoxModel<>();
        vehicleTypesModel.addElement(Driver.SONIC);
        vehicleTypesModel.addElement(Driver.PRIUS);
        vehicleTypesModel.addElement(Driver.MUSTANG);
        cmbMakeModel.setModel(vehicleTypesModel);


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DriverForm");
        frame.setContentPane(new DriverForm().pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}
