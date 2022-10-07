import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public DriverForm() {

        initializeVehicleTypeComboBox();
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
                Vehicle vehicle = Driver.getInstance().createVehicle(type);

                vehicle.setOdometer(odometer);
                vehicle.setGallonsOfGas(gallonsOfGas);
                vehicle.setMilesPerGallon(milesPerGallon);

            }
        });
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