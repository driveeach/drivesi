import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A classe Disciplina representa uma disciplina de um curso. Ela armazena os dias/horários, 
 * sala e participantes(estudantes) da disciplina, assim como o nome do professor responsável.
 * <p>
 * @author Extraido do Livro: "Programação Orientada a Objetos com Java - Uma introdução prática usando o BLUEJ
 * 
 * @version 13.03.2008
 */
public class Disciplina {
    private String professor;
    private String sala;
    private String horario;
    private List estudantes;
    private int capacidade;
    
    /**
     * Cria uma disciplina com um máximo número de matriculados. Todos os demais valores são setados 
     * para valores "padrão".
     */
    public Disciplina(int maxNumberOfStudents) {
        professor = "unknown";
        sala = "unknown";
        horario = "unknown";
        estudantes = new ArrayList();
        capacidade = maxNumberOfStudents;
    }

    /**
     * Matricule um estudante nessa disciplina.
     */
    public void enrolStudent(Estudante newStudent) {
        if(estudantes.size() == capacidade) {
            System.out.println("A disciplina está lotada, infelizmente o estudante não pode ser matriculado.");
        }
        else {
            estudantes.add(newStudent);
        }
    }
    
    /**
     * Retorne o número de estudantes atualmente matriculados.
     */
    public int numberOfStudents() {
        return estudantes.size();
    }
    
    /**
     * Atualize o número da sala dessa disciplina.
     */
    public void setRoom(String roomNumber) {
        sala = roomNumber;
    }
    
    /**
     * Atualize o horário dessa disciplina. o parâmetro deve definir o dia e o horário, 
     * tal como "Quartas e Quintas, 21h".
     */
    public void setTime(String timeAndDayString) {
        horario = timeAndDayString;
    }
    
    /**
     * Atualize o nome do instrutor/professor
     */
    public void setInstructor(String instructorName) {
        professor = instructorName;
    }
    
    /**
     * Imprima os detalhes da disciplina, incluindo a lista de estudantes matriculados
     */
    public void printList() {
        System.out.println("Disciplina " + horario);
        System.out.println("Professor: " + professor + "   sala: " + sala);
        System.out.println("Lista de Estudantes:");
        Iterator i = estudantes.iterator();
        while(i.hasNext()) {
            Estudante student = (Estudante)i.next();
            student.print();
        }
        System.out.println("Número de estudantes: " + numberOfStudents());
    }
}
