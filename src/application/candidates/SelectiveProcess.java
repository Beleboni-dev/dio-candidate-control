package candidates;
import java.util.Random;
import java.text.DecimalFormat;

public class SelectiveProcess {
    public static void main(String[] args) {
        System.out.println("Processo Seletivo");

        String[] names = {"João", "Maria", "Pedro", "Ana", "Carlos", "Laura", "José", "Mariana", "Rafael", "Lúcia", "Gustavo", "Fernanda", "Paulo", "Clara", "Lucas"};
        double[] intendedSalaries = new double[names.length];

        for (int i = 0; i < names.length; i++) {
            intendedSalaries[i] = generateRandomSalary(1700, 2200);
        }
        DecimalFormat df = new DecimalFormat("#.00");
        String[] selectedCandidates = selectCandidates(names, intendedSalaries);

        System.out.println("\nCandidatos selecionados:");
        for (String candidate : selectedCandidates) {
            System.out.println("Nome: " + candidate + " - Salário pretendido: " + df.format(intendedSalaries[findIndex(names, candidate)]));
            makeCall(candidate);
        }

        System.out.println("\nCandidatos não selecionados:");
        for (int i = 0; i < names.length; i++) {
            if (!contains(selectedCandidates, names[i])) {
                System.out.println("Nome: " + names[i] + " - Salário pretendido: " + df.format(intendedSalaries[i]));
            }
        }
    }

    static double generateRandomSalary(double min, double max) {
        return min + (max - min) * new Random().nextDouble();
    }

    static String[] selectCandidates(String[] names, double[] intendedSalaries) {
        String[] selectedCandidates = new String[5];
        int count = 0;

        for (int i = 0; i < names.length; i++) {
            if (intendedSalaries[i] <= 2000) {
                selectedCandidates[count++] = names[i];
            }

            if (count == 5) {
                break;
            }
        }

        return selectedCandidates;
    }

    static int findIndex(String[] arr, String target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    static boolean contains(String[] arr, String target) {
        for (String item : arr) {
            if (item != null && item.equals(target)) {
                return true;
            }
        }
        return false;
    }
    static void makeCall(String candidate) {
        Random random = new Random();
        int attempts = 3;
        int attemptCount = 0;

        System.out.println("Tentando ligar para " + candidate + "...");
        do {
            attemptCount++;
            boolean success = random.nextBoolean();
            if (success) {
                System.out.println("Conseguimos contato com " + candidate + " após " + attemptCount + " tentativas");
                return;
            } else {
                System.out.println("Ligação falhou, tentando denovo...");
            }
            attempts--;
        } while (attempts > 0);

        System.out.println("Não conseguimos contato com " + candidate);
    }
}

