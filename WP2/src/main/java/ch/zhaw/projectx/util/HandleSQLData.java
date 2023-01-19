package ch.zhaw.projectx.util;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HandleSQLData {

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Root1234!");
            Random random = new Random();
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(
                    "USE project_x;");


            stmt.executeUpdate(
                    "ALTER TABLE performance ADD COLUMN performance_id int; ");


            //Create data for Performance
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO performance (dtype, performance_id, name, metric, target_value) VALUES (?,?,?,?,?)");


            Metric[] metrics = {Metric.AVG, Metric.MAX, Metric.MIN, Metric.MAX};

            String[] names = {"Umsatz", "Gewinn", "Rendite", "Absatz", "Marktanteil", "Kundenbindung", "Liquidität", "Eigenkapital", "Verschuldung", "Produkt"};


            int min = 1;
            int max = 300;

            for (int i = min; i < 200; i++) {
                statement.setString(1, "aggregation");
                statement.setInt(2, random.nextInt((max - min) + 1) + min);
                statement.setString(3, names[random.nextInt(names.length)]);
                statement.setInt(4, random.nextInt(metrics.length));
                statement.setNull(5, Types.INTEGER);
                System.out.println(statement);
                statement.executeUpdate();
            }

            for (int i = 0; i < 100; i++) {
                statement.setString(1, "indicator");
                statement.setInt(2, random.nextInt((200 - min) + 1) + min);
                statement.setString(3, names[random.nextInt(names.length)]);
                statement.setNull(4, Types.VARCHAR);
                statement.setInt(5, random.nextInt(1, 100000000));
                System.out.println(statement);
                statement.executeUpdate();
            }


            // Declare the rows variable outside of the try-catch block
            List<String> reportingDatesList = new ArrayList<>();

            // Use a CSVReader to read the CSV file
            try (CSVReader reader = new CSVReader(new FileReader("WP3/reporting_dates.csv"))) {
                String[] line = null;
                // Read each row of the CSV file
                while ((line = reader.readNextSilently()) != null) {
                    // Add the row to the rows list
                    reportingDatesList.add(line[0]);
                }
                reportingDatesList.remove(0);


                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO assessment (reporting_date, performance_id, team_id) VALUES (?,?,?)");
            for (int i = 0; i < 1000; i++) {
                java.util.Date utilDate = sdf.parse(reportingDatesList.get(random.nextInt(reportingDatesList.size())));
                Date sqlDate = new Date(utilDate.getTime());
                preparedStatement.setDate(1, sqlDate);
                preparedStatement.setInt(2, random.nextInt(1, 300));
                preparedStatement.setInt(3, random.nextInt(1, 150));
                System.out.println(preparedStatement);
                preparedStatement.execute();
            }


        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }


    }
}
