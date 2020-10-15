package com.dronegcs.mavlink.spring;

import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_PARAM_COPTER;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_PARAM_I;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_PARAM_PLANE;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenerateParametersGithubDoc {

    public static void main(String[] args) throws Exception {
        FileWriter fileWriter;
        CSVWriter csvWriter;

        fileWriter = new FileWriter("ArduCopter Parameters.md");
        fileWriter.write("# ArduCopter Parameters");
        fileWriter.write("\n\n");
        csvWriter = new CSVWriter(fileWriter,'|');
        appendParametersCSV(csvWriter, MAV_PARAM_COPTER.values());
        csvWriter.close();

        fileWriter = new FileWriter("Ardupilot Parameters.md");
        fileWriter.write("# ArduPilot Parameters");
        fileWriter.write("\n\n");
        csvWriter = new CSVWriter(fileWriter,'|');
        appendParametersCSV(csvWriter, MAV_PARAM_PLANE.values());
        csvWriter.close();

        fileWriter = new FileWriter("ArduCopter Parameters List.md");
        fileWriter.write("# ArduCopter Parameters");
        fileWriter.write("\n");
        fileWriter.write("\n");
        appendParametersItems(fileWriter, MAV_PARAM_COPTER.values());
        fileWriter.close();

        return;
    }

    private static void appendParametersItems(FileWriter fileWriter, MAV_PARAM_I[] params) throws IOException {
        for (MAV_PARAM_I val : params) {
            fileWriter.write("## " + val.getName() + ": " + val.getTitle());
            fileWriter.write("\n");
            fileWriter.write(val.getDescription());
            fileWriter.write("\n");
            fileWriter.write("Unit: " + (val.getUnit() == null ? "scalar" : val.getUnit()));
            fileWriter.write("\n");
            fileWriter.write("Default Value: " + val.getDefaultValue());
            fileWriter.write("\n");
            if (val.getRange() != null) {
                fileWriter.write("\n");
                fileWriter.write("Min | Max");
                fileWriter.write("\n");
                fileWriter.write("--- | ---");
                fileWriter.write("\n");
                fileWriter.write(" " + val.getRange().getMin() + " | " + val.getRange().getMax());
            }
            else if (val.getOptions() != null) {
                fileWriter.write("\n");
                fileWriter.write("Key | Value");
                fileWriter.write("\n");
                fileWriter.write("--- | ---");
                fileWriter.write("\n");
                for (Map.Entry<Number, String> option : val.getOptions().entrySet()) {
                    fileWriter.write(option.getKey() + " | " + option.getValue());
                    fileWriter.write("\n");
                }
            }
            else {
            }
            fileWriter.write("\n");
            fileWriter.write("\n");
        }
    }

    private static void appendParametersCSV(CSVWriter csvWriter, MAV_PARAM_I[] params) {
        csvWriter.writeNext(new String[]{"Name "," Possible Value "," Increment "," Unit "," Range ", " Read Only ", " Title " ," Description"}, false);
        csvWriter.writeNext(new String[]{"--- "," --- "," --- "," --- "," --- ", " ---", " --- " ," ---"},false);
        for (MAV_PARAM_I val : params) {
            List<String> line = new ArrayList();
            line.add(val.getName() + " ");
            line.add(" " + val.getDefaultValue() + " ");
            line.add(" " + val.getIncrement() + " ");
            line.add(" " + val.getUnit() + " ");
//            line.add(val) //range
            if (val.getRange() != null) {
                line.add(" " + val.getRange().getMin() + " - " + val.getRange().getMax() + " ");
            }
            else if (val.getOptions() != null) {
                String optionString = " ";
                for (Map.Entry<Number, String> option : val.getOptions().entrySet()) {
                    optionString += option.getKey() + ":" + option.getValue() + "<br/>";
                }
                line.add(optionString + " ");
            }
            else {
                line.add(" ");
            }
            line.add(" " + (val.isReadOnly() ? "V" : "")  + " ");
            line.add(" " + val.getTitle() + " ");
            line.add(" " + val.getDescription());
            String[] res = line.toArray(new String[0]);
            csvWriter.writeNext(res,false);
        }
    }

}
