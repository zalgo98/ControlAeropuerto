/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.controlaeropuertos;


import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author gonzalo
 */
public class Control extends javax.swing.JFrame {
    
    private final Aeropuerto aeropuerto_Madrid;
    private final Aeropuerto aeropuerto_Barcelona;
    private Actualizar actuHangarMa;
    private Actualizar actuEstaMa;
    private Actualizar actuRodajeMa;
    private Actualizar actuTallerMa;
    private Actualizar actuPuertaMa;
    private Actualizar actuHangarBa;
    private Actualizar actuEstaBa;
    private Actualizar actuRodajeBa;
    private Actualizar actuTallerBa;
    private Actualizar actuPuertaBa;
    
    


    public Control(Aeropuerto aeropuerto_Madrid, Aeropuerto aeropuerto_Barcelona) {
        initComponents();
        this.aeropuerto_Barcelona = aeropuerto_Barcelona;
        this.aeropuerto_Madrid = aeropuerto_Madrid;

    }

    public void mostrar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1202, 800);
        setLocationRelativeTo(null); 
        JTextField[] puertas = { Puerta1, Puerta2, Puerta3, Puerta4, Puerta5, Puerta6 };
        JTextField[] pistas = { Pista1, Pista2, Pista3, Pista4 };
        JTextField[] puertasB = { Puerta1B, Puerta2B, Puerta3B, Puerta4B, Puerta5B, Puerta6B };
        JTextField[] pistasB = { Pista1B, Pista2B, Pista3B, Pista4B };



      // Madrid
        
                Num_pasajeros.setText(String.valueOf(aeropuerto_Madrid.getPasajerosDisponibles()));
                for (Autobus bus : aeropuerto_Madrid.getAutobuses()) {
                    if(bus.getIda()){
                        actualizarBus(bus, Bus_Aeropuerto);
                    }else if(!bus.getIda()){
                        actualizarBus(bus, Bus_ciudad);
                    }
                }
                actuHangarMa= new Actualizar(aeropuerto_Madrid.getHangar().getAviones(), Hangar);
                actuHangarMa.start();
                actuEstaMa= new Actualizar(aeropuerto_Madrid.getAreaEstacionamiento().getAvionesEnEspera(), Estacionamiento);
                actuEstaMa.start();
                for (int i = 0; i < puertas.length; i++) {
                    PuertaEmbarque puerta = aeropuerto_Madrid.getPuertasEmbarque().get(i);
                    actuPuertaMa= new Actualizar(puerta.getAvionesEmbarque(), puertas[i]);
                    actuPuertaMa.start();           
                }
                
                actuRodajeMa= new Actualizar(aeropuerto_Madrid.getAreaRodaje().getAvionEmbarques(), A_Rodaje);
                actuRodajeMa.start();
                
                for (int i = 0; i < aeropuerto_Madrid.getPistas().size(); i++) {
                   Avion avion = aeropuerto_Madrid.getPistas().get(i).getAvionAsignado();
                    if(avion!=null){
                        pistas[i].setText(avion.getNombre());
                    }else{
                        pistas[i].setText("");
                    }
                }
                actuTallerMa= new Actualizar(aeropuerto_Madrid.getTaller().getAvionesEnIspeccion(), Taller);
                actuTallerMa.start();
            
                
            
             // Barcelona

            synchronized(aeropuerto_Barcelona){
               

                pasajerosB.setText(String.valueOf(aeropuerto_Barcelona.getPasajerosDisponibles()));

                for (Autobus bus : aeropuerto_Barcelona.getAutobuses()) {
                    if(bus.getIda()){
                    actualizarBus(bus, BusABarcelona);
                }else if(!bus.getIda()){
                    actualizarBus(bus, BusCBarcelona);
                }
                }
                actuHangarBa= new Actualizar(aeropuerto_Barcelona.getHangar().getAviones(), Hangar1);
                actuHangarBa.start();
                actuEstaBa= new Actualizar(aeropuerto_Barcelona.getAreaEstacionamiento().getAvionesEnEspera(), Estacionamiento1);
                actuEstaBa.start();
                for (int i = 0; i < puertasB.length; i++) {
                    PuertaEmbarque puerta = aeropuerto_Barcelona.getPuertasEmbarque().get(i);
                    actuPuertaBa= new Actualizar(puerta.getAvionesEmbarque(), puertasB[i]);
                    actuPuertaBa.start();           
                }
                actuRodajeBa= new Actualizar(aeropuerto_Barcelona.getAreaRodaje().getAvionEmbarques(), A_Rodaje1);
                actuRodajeBa.start();
                for (int i = 0; i < aeropuerto_Barcelona.getPistas().size(); i++) {
                  Avion avion = aeropuerto_Barcelona.getPistas().get(i).getAvionAsignado();
                    if(avion!=null){
                        pistasB[i].setText(avion.getNombre());
                    }else{
                        pistasB[i].setText("");
                    }
                }
                actuTallerBa= new Actualizar(aeropuerto_Barcelona.getTaller().getAvionesEnIspeccion(), Taller2);
                actuTallerBa.start();
            }

                // aerovias
                M_B.setText(aeropuerto_Madrid.getAerovia().avionesAerolinea());
                B_M.setText(aeropuerto_Barcelona.getAerovia().avionesAerolinea());

            
    }
    public void actualizarBus(Autobus bus, JTextField textoBus) {
    if (bus != null) {
        if (bus.getIda() && bus.getPasajerosEnBus_Ida() > 0) {
            textoBus.setText(bus.IdBus() + "(" + bus.getPasajerosEnBus_Ida() + ")");
        } else if (!bus.getIda() && bus.getPasajerosEnBus_Vuelta() > 0) {
            textoBus.setText(bus.IdBus() + "(" + bus.getPasajerosEnBus_Vuelta() + ")");
        } else {
            textoBus.setText("");
        }
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Puerta1B = new javax.swing.JTextField();
        Puerta2B = new javax.swing.JTextField();
        Puerta3B = new javax.swing.JTextField();
        Puerta4B = new javax.swing.JTextField();
        Taller = new javax.swing.JTextField();
        Puerta6B = new javax.swing.JTextField();
        Puerta1 = new javax.swing.JTextField();
        Puerta5B = new javax.swing.JTextField();
        Puerta2 = new javax.swing.JTextField();
        A_Rodaje1 = new javax.swing.JTextField();
        Puerta3 = new javax.swing.JTextField();
        Pista1B = new javax.swing.JTextField();
        Puerta4 = new javax.swing.JTextField();
        Puerta6 = new javax.swing.JTextField();
        Puerta5 = new javax.swing.JTextField();
        A_Rodaje = new javax.swing.JTextField();
        Pista1 = new javax.swing.JTextField();
        Pista2 = new javax.swing.JTextField();
        Pista3 = new javax.swing.JTextField();
        Pista4 = new javax.swing.JTextField();
        Estacionamiento = new javax.swing.JTextField();
        M_B = new javax.swing.JTextField();
        B_M = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        BusABarcelona = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        BusCBarcelona = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Taller2 = new javax.swing.JTextField();
        pasajerosB = new javax.swing.JTextField();
        Pista2B = new javax.swing.JTextField();
        Pista3B = new javax.swing.JTextField();
        Pista4B = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Estacionamiento1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        puerta_1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Puerta_2 = new javax.swing.JLabel();
        puerta_2 = new javax.swing.JLabel();
        Puerta_3 = new javax.swing.JLabel();
        Puerta_7 = new javax.swing.JLabel();
        Puerta_5 = new javax.swing.JLabel();
        Puerta_4 = new javax.swing.JLabel();
        Puerta_6 = new javax.swing.JLabel();
        Rodaje = new javax.swing.JLabel();
        Puerta_8 = new javax.swing.JLabel();
        Puerta_9 = new javax.swing.JLabel();
        Puerta_10 = new javax.swing.JLabel();
        Puerta_11 = new javax.swing.JLabel();
        Pista_1 = new javax.swing.JLabel();
        Rodaje1 = new javax.swing.JLabel();
        Pista_2 = new javax.swing.JLabel();
        Pista_3 = new javax.swing.JLabel();
        Pista_4 = new javax.swing.JLabel();
        Pista_5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        Pista_6 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        Hangar1 = new javax.swing.JTextField();
        Bus_Aeropuerto = new javax.swing.JTextField();
        Bus_ciudad = new javax.swing.JTextField();
        Num_pasajeros = new javax.swing.JTextField();
        Hangar = new javax.swing.JTextField();
        Reanurar = new javax.swing.JToggleButton();
        Pausar = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Puerta1B.setEditable(false);
        Puerta1B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Puerta1BActionPerformed(evt);
            }
        });

        Puerta2B.setEditable(false);
        Puerta2B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Puerta2BActionPerformed(evt);
            }
        });

        Puerta3B.setEditable(false);
        Puerta3B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Puerta3BActionPerformed(evt);
            }
        });

        Puerta4B.setEditable(false);
        Puerta4B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Puerta4BActionPerformed(evt);
            }
        });

        Taller.setEditable(false);
        Taller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TallerActionPerformed(evt);
            }
        });

        Puerta6B.setEditable(false);
        Puerta6B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Puerta6BActionPerformed(evt);
            }
        });

        Puerta1.setEditable(false);
        Puerta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Puerta1ActionPerformed(evt);
            }
        });

        Puerta5B.setEditable(false);
        Puerta5B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Puerta5BActionPerformed(evt);
            }
        });

        Puerta2.setEditable(false);
        Puerta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Puerta2ActionPerformed(evt);
            }
        });

        A_Rodaje1.setEditable(false);
        A_Rodaje1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A_Rodaje1ActionPerformed(evt);
            }
        });

        Puerta3.setEditable(false);
        Puerta3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Puerta3ActionPerformed(evt);
            }
        });

        Pista1B.setEditable(false);
        Pista1B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pista1BActionPerformed(evt);
            }
        });

        Puerta4.setEditable(false);
        Puerta4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Puerta4ActionPerformed(evt);
            }
        });

        Puerta6.setEditable(false);
        Puerta6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Puerta6ActionPerformed(evt);
            }
        });

        Puerta5.setEditable(false);
        Puerta5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Puerta5ActionPerformed(evt);
            }
        });

        A_Rodaje.setEditable(false);
        A_Rodaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A_RodajeActionPerformed(evt);
            }
        });

        Pista1.setEditable(false);
        Pista1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pista1ActionPerformed(evt);
            }
        });

        Pista2.setEditable(false);
        Pista2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pista2ActionPerformed(evt);
            }
        });

        Pista3.setEditable(false);
        Pista3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pista3ActionPerformed(evt);
            }
        });

        Pista4.setEditable(false);
        Pista4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pista4ActionPerformed(evt);
            }
        });

        Estacionamiento.setEditable(false);
        Estacionamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstacionamientoActionPerformed(evt);
            }
        });

        M_B.setEditable(false);
        M_B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M_BActionPerformed(evt);
            }
        });

        B_M.setEditable(false);
        B_M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_MActionPerformed(evt);
            }
        });

        jLabel22.setText("Bus Aeropuerto");

        jLabel23.setText("Bus Ciudad");

        jLabel26.setText("Nº pasajeros en el Aeropuerto");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Control Aeropuertos");

        BusABarcelona.setEditable(false);
        BusABarcelona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BusABarcelonaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Aeropuerto de Madrid");

        BusCBarcelona.setEditable(false);
        BusCBarcelona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BusCBarcelonaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Aeropuerto de Barcelona");

        jLabel4.setText("Bus Aeropuerto");

        jLabel5.setText("Bus Ciudad");

        jLabel6.setText("Nº pasajeros en el Aeropuerto");

        Taller2.setEditable(false);
        Taller2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Taller2ActionPerformed(evt);
            }
        });

        pasajerosB.setEditable(false);
        pasajerosB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasajerosBActionPerformed(evt);
            }
        });

        Pista2B.setEditable(false);
        Pista2B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pista2BActionPerformed(evt);
            }
        });

        Pista3B.setEditable(false);
        Pista3B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pista3BActionPerformed(evt);
            }
        });

        Pista4B.setEditable(false);
        Pista4B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pista4BActionPerformed(evt);
            }
        });

        jLabel7.setText("Hangar");

        Estacionamiento1.setEditable(false);
        Estacionamiento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Estacionamiento1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Taller");

        jLabel10.setText("Hangar");

        jLabel9.setText("Area Estacionamiento");

        jLabel11.setText("Taller");

        puerta_1.setText("Puerta 1");

        jLabel12.setText("Area Estacionamiento");

        Puerta_2.setText("Puerta 2");

        puerta_2.setText("Puerta 1");

        Puerta_3.setText("Puerta 3");

        Puerta_7.setText("Puerta 2");

        Puerta_5.setText("Puerta 5");

        Puerta_4.setText("Puerta 4");

        Puerta_6.setText("Puerta 6");

        Rodaje.setText("Area de rodaje");

        Puerta_8.setText("Puerta 3");

        Puerta_9.setText("Puerta 5");

        Puerta_10.setText("Puerta 4");

        Puerta_11.setText("Puerta 6");

        Pista_1.setText("Pista 1");

        Rodaje1.setText("Area de rodaje");

        Pista_2.setText("Pista 2");

        Pista_3.setText("Pista 1");

        Pista_4.setText("Pista 4");

        Pista_5.setText("Pista 2");

        jLabel20.setText("Aerovia Madrid-Barcelona");

        Pista_6.setText("Pista 4");

        jLabel21.setText("Aerovia Barcelona-Madrid");

        jLabel40.setText("Pista 3");

        jLabel24.setText("Pista 3");

        Hangar1.setEditable(false);
        Hangar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hangar1ActionPerformed(evt);
            }
        });

        Bus_Aeropuerto.setEditable(false);
        Bus_Aeropuerto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bus_AeropuertoActionPerformed(evt);
            }
        });

        Bus_ciudad.setEditable(false);
        Bus_ciudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bus_ciudadActionPerformed(evt);
            }
        });

        Num_pasajeros.setEditable(false);
        Num_pasajeros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Num_pasajerosActionPerformed(evt);
            }
        });

        Hangar.setEditable(false);
        Hangar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HangarActionPerformed(evt);
            }
        });

        Reanurar.setSelected(true);
        Reanurar.setText("Reanurar");
        Reanurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReanurarActionPerformed(evt);
            }
        });

        Pausar.setText("Pausar");
        Pausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PausarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(24, 24, 24)
                        .addComponent(Num_pasajeros, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(Estacionamiento))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(Puerta_3)
                                            .addGap(18, 18, 18)
                                            .addComponent(Puerta3))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(puerta_1)
                                                .addComponent(Puerta_2))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(Puerta1)
                                                .addComponent(Puerta2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(Pista_2)
                                            .addGap(18, 18, 18)
                                            .addComponent(Pista2))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(Pista_1)
                                            .addGap(18, 18, 18)
                                            .addComponent(Pista1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(24, 24, 24)
                                        .addComponent(Bus_Aeropuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Puerta_6)
                                        .addGap(18, 18, 18)
                                        .addComponent(Puerta6))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Puerta_5)
                                        .addGap(18, 18, 18)
                                        .addComponent(Puerta5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Puerta_4)
                                        .addGap(18, 18, 18)
                                        .addComponent(Puerta4, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addGap(18, 18, 18)
                                        .addComponent(Pista3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Pista_4)
                                        .addGap(18, 18, 18)
                                        .addComponent(Pista4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(Bus_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Taller)
                                    .addComponent(Hangar)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Rodaje)
                                .addGap(18, 18, 18)
                                .addComponent(A_Rodaje, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(24, 24, 24)
                                .addComponent(pasajerosB, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(BusABarcelona, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(BusCBarcelona, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(67, 67, 67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(Estacionamiento1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(Puerta_8)
                                            .addGap(18, 18, 18)
                                            .addComponent(Puerta3B))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(puerta_2)
                                                .addComponent(Puerta_7))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(Puerta1B)
                                                .addComponent(Puerta2B, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(Pista_5)
                                            .addGap(18, 18, 18)
                                            .addComponent(Pista2B))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(Pista_3)
                                            .addGap(18, 18, 18)
                                            .addComponent(Pista1B, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Puerta_11)
                                        .addGap(18, 18, 18)
                                        .addComponent(Puerta6B))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Puerta_9)
                                        .addGap(18, 18, 18)
                                        .addComponent(Puerta5B))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Puerta_10)
                                        .addGap(18, 18, 18)
                                        .addComponent(Puerta4B, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel40)
                                        .addGap(18, 18, 18)
                                        .addComponent(Pista3B, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Pista_6)
                                        .addGap(18, 18, 18)
                                        .addComponent(Pista4B, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Hangar1)
                                    .addComponent(Taller2)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Rodaje1)
                                .addGap(18, 18, 18)
                                .addComponent(A_Rodaje1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59))))
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Pausar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Reanurar, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                .addGap(138, 138, 138))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(513, 513, 513)
                                .addComponent(jLabel20))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(510, 510, 510)
                                .addComponent(jLabel21)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(M_B, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(B_M, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Reanurar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Pausar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(Bus_Aeropuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Bus_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Num_pasajeros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(BusABarcelona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BusCBarcelona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pasajerosB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(Hangar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(Taller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(Estacionamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(puerta_1)
                            .addComponent(Puerta_4)
                            .addComponent(Puerta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Puerta4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Puerta_2)
                            .addComponent(Puerta_5)
                            .addComponent(Puerta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Puerta5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Puerta_3)
                            .addComponent(Puerta_6)
                            .addComponent(Puerta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Puerta6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Rodaje)
                            .addComponent(A_Rodaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Pista_1)
                            .addComponent(jLabel24)
                            .addComponent(Pista1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Pista3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Pista_2)
                            .addComponent(Pista_4)
                            .addComponent(Pista2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Pista4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(Hangar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(Taller2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(Estacionamiento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(puerta_2)
                            .addComponent(Puerta_10)
                            .addComponent(Puerta1B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Puerta4B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Puerta_7)
                            .addComponent(Puerta_9)
                            .addComponent(Puerta2B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Puerta5B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Puerta_8)
                            .addComponent(Puerta_11)
                            .addComponent(Puerta3B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Puerta6B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Rodaje1)
                            .addComponent(A_Rodaje1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Pista_3)
                            .addComponent(jLabel40)
                            .addComponent(Pista1B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Pista3B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Pista_5)
                            .addComponent(Pista_6)
                            .addComponent(Pista2B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Pista4B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addGap(7, 7, 7)
                .addComponent(M_B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B_M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Puerta1BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Puerta1BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Puerta1BActionPerformed

    private void Puerta2BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Puerta2BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Puerta2BActionPerformed

    private void Puerta3BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Puerta3BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Puerta3BActionPerformed

    private void Puerta4BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Puerta4BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Puerta4BActionPerformed

    private void TallerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TallerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TallerActionPerformed

    private void Puerta6BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Puerta6BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Puerta6BActionPerformed

    private void Puerta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Puerta1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Puerta1ActionPerformed

    private void Puerta5BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Puerta5BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Puerta5BActionPerformed

    private void Puerta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Puerta2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Puerta2ActionPerformed

    private void A_Rodaje1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A_Rodaje1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_A_Rodaje1ActionPerformed

    private void Puerta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Puerta3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Puerta3ActionPerformed

    private void Pista1BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pista1BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pista1BActionPerformed

    private void Puerta4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Puerta4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Puerta4ActionPerformed

    private void Puerta6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Puerta6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Puerta6ActionPerformed

    private void Puerta5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Puerta5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Puerta5ActionPerformed

    private void A_RodajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A_RodajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_A_RodajeActionPerformed

    private void Pista1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pista1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pista1ActionPerformed

    private void Pista2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pista2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pista2ActionPerformed

    private void Pista3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pista3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pista3ActionPerformed

    private void Pista4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pista4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pista4ActionPerformed

    private void EstacionamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstacionamientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EstacionamientoActionPerformed

    private void M_BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_M_BActionPerformed

    private void B_MActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_MActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_MActionPerformed

    private void BusABarcelonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BusABarcelonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BusABarcelonaActionPerformed

    private void BusCBarcelonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BusCBarcelonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BusCBarcelonaActionPerformed

    private void Taller2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Taller2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Taller2ActionPerformed

    private void pasajerosBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasajerosBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pasajerosBActionPerformed

    private void Pista2BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pista2BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pista2BActionPerformed

    private void Pista3BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pista3BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pista3BActionPerformed

    private void Pista4BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pista4BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pista4BActionPerformed

    private void Estacionamiento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Estacionamiento1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Estacionamiento1ActionPerformed

    private void Hangar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hangar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Hangar1ActionPerformed

    private void Bus_AeropuertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bus_AeropuertoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Bus_AeropuertoActionPerformed

    private void Bus_ciudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bus_ciudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Bus_ciudadActionPerformed

    private void Num_pasajerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Num_pasajerosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Num_pasajerosActionPerformed

    private void HangarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HangarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HangarActionPerformed

    private void PausarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PausarActionPerformed
        // TODO add your handling code here:
        if(Pausar.isSelected()){
            aeropuerto_Madrid.pausar();
            aeropuerto_Barcelona.pausar();
            Reanurar.setSelected(false);
        }
    }//GEN-LAST:event_PausarActionPerformed

    private void ReanurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReanurarActionPerformed
        // TODO add your handling code here:
        if(Reanurar.isSelected()){
        aeropuerto_Madrid.reanudar();
        aeropuerto_Barcelona.reanudar();
        Pausar.setSelected(false);
        }
    }//GEN-LAST:event_ReanurarActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField A_Rodaje;
    private javax.swing.JTextField A_Rodaje1;
    private javax.swing.JTextField B_M;
    private javax.swing.JTextField BusABarcelona;
    private javax.swing.JTextField BusCBarcelona;
    private javax.swing.JTextField Bus_Aeropuerto;
    private javax.swing.JTextField Bus_ciudad;
    private javax.swing.JTextField Estacionamiento;
    private javax.swing.JTextField Estacionamiento1;
    private javax.swing.JTextField Hangar;
    private javax.swing.JTextField Hangar1;
    private javax.swing.JTextField M_B;
    private javax.swing.JTextField Num_pasajeros;
    private javax.swing.JToggleButton Pausar;
    private javax.swing.JTextField Pista1;
    private javax.swing.JTextField Pista1B;
    private javax.swing.JTextField Pista2;
    private javax.swing.JTextField Pista2B;
    private javax.swing.JTextField Pista3;
    private javax.swing.JTextField Pista3B;
    private javax.swing.JTextField Pista4;
    private javax.swing.JTextField Pista4B;
    private javax.swing.JLabel Pista_1;
    private javax.swing.JLabel Pista_2;
    private javax.swing.JLabel Pista_3;
    private javax.swing.JLabel Pista_4;
    private javax.swing.JLabel Pista_5;
    private javax.swing.JLabel Pista_6;
    private javax.swing.JTextField Puerta1;
    private javax.swing.JTextField Puerta1B;
    private javax.swing.JTextField Puerta2;
    private javax.swing.JTextField Puerta2B;
    private javax.swing.JTextField Puerta3;
    private javax.swing.JTextField Puerta3B;
    private javax.swing.JTextField Puerta4;
    private javax.swing.JTextField Puerta4B;
    private javax.swing.JTextField Puerta5;
    private javax.swing.JTextField Puerta5B;
    private javax.swing.JTextField Puerta6;
    private javax.swing.JTextField Puerta6B;
    private javax.swing.JLabel Puerta_10;
    private javax.swing.JLabel Puerta_11;
    private javax.swing.JLabel Puerta_2;
    private javax.swing.JLabel Puerta_3;
    private javax.swing.JLabel Puerta_4;
    private javax.swing.JLabel Puerta_5;
    private javax.swing.JLabel Puerta_6;
    private javax.swing.JLabel Puerta_7;
    private javax.swing.JLabel Puerta_8;
    private javax.swing.JLabel Puerta_9;
    private javax.swing.JToggleButton Reanurar;
    private javax.swing.JLabel Rodaje;
    private javax.swing.JLabel Rodaje1;
    private javax.swing.JTextField Taller;
    private javax.swing.JTextField Taller2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField pasajerosB;
    private javax.swing.JLabel puerta_1;
    private javax.swing.JLabel puerta_2;
    // End of variables declaration//GEN-END:variables
}
