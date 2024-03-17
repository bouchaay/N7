package ShemaTableElements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PythonScriptExecutor {

    List<ColumnA<?>> input;
    AlgorithmA algorithm;
    List<String> outputValues;
    /**
     * Constructeur de la classe PythonScriptExecutor
     * @param algorithm L'algorithme
     */
    public PythonScriptExecutor(List<ColumnA<?>> input, AlgorithmA algorithm) {
        this.input = input;
        this.algorithm = algorithm;
        this.outputValues = new ArrayList<>();
    }

    /**
     * Exécute l'algorithme
     */
    public void execute() {
        List<String> command = new ArrayList<>();
        command.add("python");
        command.add(algorithm.getPath());
        for (ColumnA<?> column : input) {
            command.add(listToString(column.getValues()));
        }

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        // Redirige la sortie d'erreur vers la sortie standard
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                outputValues.add(line);
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static <T> String listToString(List<T> list) {
        String result = "[";
        for (T element : list) {
            result += element.toString() + ", ";
        }
        result = result.substring(0, result.length() - 2);
        result += "]";
        return result;
    }

    protected OutputA createOutput() {
        // Vérifier la nature des valeurs de sortie
        ColumnDataTypeA dataType = this.algorithm.getInput().getDataType();
        OutputA newOutput;
        // Créer l'instance de l'output
        switch (dataType) {
            case INTEGER:
                newOutput = new OutputA("Output" + algorithm.getName(), ColumnDataTypeA.INTEGER);
            case FLOAT:
                newOutput = new OutputA("Output" + algorithm.getName(), ColumnDataTypeA.FLOAT);
            case STRING:
                newOutput = new OutputA("Output" + algorithm.getName(), ColumnDataTypeA.STRING);
            default:
                newOutput = new OutputA("Output" + algorithm.getName(), ColumnDataTypeA.STRING);
        }

        // Ajouter les valeurs à l'output
        for (String value : outputValues) {
            switch (dataType) {
                case INTEGER:
                    newOutput.addValue(Integer.parseInt(value));
                case FLOAT:
                    newOutput.addValue(Float.parseFloat(value));
                case STRING:
                    newOutput.addValue(value);
                default:
                    newOutput.addValue(value);
            }
        }
		return newOutput;
    }
}