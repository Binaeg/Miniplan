package Miniplan.GUI;

import Miniplan.Document.Export;
import Miniplan.Miniplan.Miniplan;
import Miniplan.Minis.MiniRepository;
import Miniplan.POJO.Mini;
import Miniplan.POJO.Termin;
import Miniplan.Termine.TerminRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Objects;

public class GUI implements ActionListener {
    private JFrame jFrame;
    private Container contentPane;

    private JLabel jLabelChangeMinilist;
    private JTextField jTextFieldMiniListPath;
    private JButton jButtonSearchMiniList;

    private JLabel jLabelChangeTerminlist;
    private JTextField jTextFieldTerminListPath;
    private JButton jButtonSearchTerminList;
    private JHyperlink jHyperlinkSesam;
    private JLabel jLabelTerminDownloadExplanation;

    private JLabel jLabelOutput;
    private JTextField jTextFieldOutputPath;
    private JButton jButtonSearchOutput;

    private JButton jButtonCreateMiniplan;

    private JTextArea jTextAreaLogging;
    private JScrollPane jScrollPaneLogging;

    private final int leftBound = 40;
    private final int topBound = 25;
    private final int height = 25;

    private Miniplan miniplan;

    public GUI() {
        initUI();

        createComponents();

        addComponents();

        setLocationAndSizeOfComponents();

        miniplan = new Miniplan(this);
    }

    private void initUI() {
        jFrame = new JFrame("Miniplan erstellen");
        jFrame.setSize(800, 600);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = jFrame.getContentPane();
    }

    private void createComponents() {
        jLabelChangeMinilist = new JLabel("Mini-Liste ändern:");
        jTextFieldMiniListPath = new JTextField();
        jButtonSearchMiniList = new JButton("Durchsuchen");
        jButtonSearchMiniList.addActionListener(this);

        jLabelChangeTerminlist = new JLabel("Termin-Liste ändern:");
        jTextFieldTerminListPath = new JTextField();
        jButtonSearchTerminList = new JButton("Durchsuchen");
        jButtonSearchTerminList.addActionListener(this);

        jHyperlinkSesam = new JHyperlink("Zum Download der Termin-Liste", "https://edith6-1.w-commerce.de/sesam/edith/elem/3614");

        jLabelTerminDownloadExplanation = new JLabel("Erklärung zum Termin-Download");
        jLabelTerminDownloadExplanation.setForeground(Color.BLUE.darker());
        jLabelTerminDownloadExplanation.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLabelTerminDownloadExplanation.addMouseListener(new MouseAdapter() {
            private String text = jLabelTerminDownloadExplanation.getText();

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabelTerminDownloadExplanation.setText(String.format("<html><a href=''>%s</a></html>", text));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabelTerminDownloadExplanation.setText(text);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel lbl = new JLabel(new ImageIcon(Objects.requireNonNull(GUI.class.getResource("Erklärung_Export_Terminplan.png"))));
                JOptionPane.showMessageDialog(jFrame, lbl, "Erklärung zum Download des Terminplans",
                        JOptionPane.INFORMATION_MESSAGE, null);
            }
        });


        jLabelOutput = new JLabel("Speicherort für Ausgabe-Datei wählen:");
        jTextFieldOutputPath = new JTextField();
        jButtonSearchOutput = new JButton("Durchsuchen");
        jButtonSearchOutput.addActionListener(this);

        jButtonCreateMiniplan = new JButton("Miniplan erstellen");
        jButtonCreateMiniplan.addActionListener(this);

        jTextAreaLogging = new JTextArea();
        jScrollPaneLogging = new JScrollPane(jTextAreaLogging);
        jTextAreaLogging.setEditable(false);
        jTextAreaLogging.setLineWrap(true);
        jTextAreaLogging.append(new Timestamp(System.currentTimeMillis()) + "\tMiniplan Programm erfolgreich gestartet.");
    }

    private void addComponents() {
        contentPane.add(jLabelChangeMinilist);
        contentPane.add(jTextFieldMiniListPath);
        contentPane.add(jButtonSearchMiniList);

        contentPane.add(jLabelChangeTerminlist);
        contentPane.add(jTextFieldTerminListPath);
        contentPane.add(jButtonSearchTerminList);

        contentPane.add(jHyperlinkSesam);
        contentPane.add(jLabelTerminDownloadExplanation);

        contentPane.add(jLabelOutput);
        contentPane.add(jTextFieldOutputPath);
        contentPane.add(jButtonSearchOutput);

        contentPane.add(jButtonCreateMiniplan);

        contentPane.add(jScrollPaneLogging);
    }

    private void setLocationAndSizeOfComponents() {
        contentPane.setLayout(null);
        jLabelChangeMinilist.setBounds(leftBound, topBound, 400, height);
        jTextFieldMiniListPath.setBounds(leftBound, jLabelChangeMinilist.getY() + 30, 530, height);
        jButtonSearchMiniList.setBounds(leftBound + jTextFieldMiniListPath.getWidth() + 20, jTextFieldMiniListPath.getY(), 150, height);

        jLabelChangeTerminlist.setBounds(leftBound, jButtonSearchMiniList.getY() + 50, 400, height);
        jTextFieldTerminListPath.setBounds(leftBound, jLabelChangeTerminlist.getY() + 30, 530, height);
        jButtonSearchTerminList.setBounds(leftBound + jTextFieldTerminListPath.getWidth() + 20, jTextFieldTerminListPath.getY(), 150, height);

        jHyperlinkSesam.setBounds(leftBound, jButtonSearchTerminList.getY() + 30, 200, height);
        jLabelTerminDownloadExplanation.setBounds(leftBound + jHyperlinkSesam.getWidth() + 20, jButtonSearchTerminList.getY() + 30, 400, height);


        jLabelOutput.setBounds(leftBound, jHyperlinkSesam.getY() + 50, 400, height);
        jTextFieldOutputPath.setBounds(leftBound, jLabelOutput.getY() + 30, 530, height);
        jButtonSearchOutput.setBounds(leftBound + jTextFieldOutputPath.getWidth() + 20, jTextFieldOutputPath.getY(), 150, height);

        jButtonCreateMiniplan.setBounds(leftBound, jButtonSearchOutput.getY() + 80, 150, height);

        jScrollPaneLogging.setBounds(leftBound, jButtonCreateMiniplan.getY() + 50, 700, 170);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButtonSearchMiniList) {
            onPressSearchMiniList();
        }

        if (e.getSource() == jButtonSearchTerminList) {
            onPressSearchTerminList();
        }

        if (e.getSource() == jButtonSearchOutput) {
            onPressSearchOutput();
        }

        if (e.getSource() == jButtonCreateMiniplan) {
            onPressCreateMiniplan();
        }
    }

    private void onPressSearchMiniList() {
        String miniPath = executeFileChooser();
        MiniRepository.setMinisPath(miniPath);
        jTextFieldMiniListPath.setText(miniPath);
        log("Pfad für Minis gesetzt: " + miniPath);
        miniplan.initMinis(true);
    }

    private void onPressSearchTerminList() {
        String terminPath = executeFileChooser();
        TerminRepository.setTerminePath(terminPath);
        jTextFieldTerminListPath.setText(terminPath);
        log("Pfad für Termine gesetzt: " + terminPath);
        miniplan.initTermine(true);
    }

    private void onPressSearchOutput() {
        String outputPath = executePathChooser();
        Export.setExportPath(outputPath);
        jTextFieldOutputPath.setText(outputPath);
        log("Pfad für Ausgabe gesetzt: " + outputPath);
    }

    private void onPressCreateMiniplan() {
        log("Miniplan erstellen gestartet");
        miniplan.deployMinis();

        try {
            Export exportDoc = new Export();
            for (Termin termin : miniplan.getTermine()) {
                termin.getDeployedMinis().sort(Comparator.comparing(Mini::getBirthdate));
                exportDoc.printLineToDocument(String.format("%s, %s\n", termin.getWeekday().getName(), termin.getDate()));
                exportDoc.printLineToDocument(String.format(termin.getTime() + "\t\t" + termin.deployedMinisToString() + "\n"));
            }

            miniplan.getMinis().sort(Comparator.comparing(Mini::getAmountOfEntries));
            for (Mini mini : miniplan.getMinis()) {
                log("Einsätze: " + mini.getAmountOfEntries() + "\t" +  mini.getName() + " ");
            }
            exportDoc.finishDocument();
            log("Miniplan erfolgreich exportiert");
        } catch (IOException e) {
            log("Fehler beim Miniplan erstellen: " + e.getMessage());
        }
    }

    private String executeFileChooser() {
        JFileChooser jFileChooser = new JFileChooser();
        int returnOfFileChooser = jFileChooser.showOpenDialog(null);
        if (returnOfFileChooser == JFileChooser.APPROVE_OPTION) {
            return jFileChooser.getSelectedFile().getAbsolutePath().replace("\\", "/");
        } else {
            return "";
        }
    }

    private String executePathChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Speicherort auswählen");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getPath().replace("\\", "/");
        } else {
            return "";
        }
    }

    public void log(String message) {
        jTextAreaLogging.append("\n" + new Timestamp(System.currentTimeMillis()) + "\t" + message);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(GUI::new);
    }
}
