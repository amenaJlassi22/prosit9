import java.util.*; 

class Employe {
    private int id; 
    private String nom, prenom, nomDepartement; 
    private int grade; 
    
    public Employe() {} 
    public Employe(int id, String nom, String prenom, String nomDepartement, int grade) { 
        this.id = id; this.nom = nom; this.prenom = prenom; this.nomDepartement = nomDepartement; this.grade = grade; 
    }
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getNomDepartement() { return nomDepartement; }
    public int getGrade() { return grade; }

    @Override
    public boolean equals(Object o) { 
        if (this == o) return true; 
        if (o == null || getClass() != o.getClass()) return false; 
        Employe e = (Employe) o; 
        return id == e.id && Objects.equals(nom, e.nom); 
    }

    @Override
    public String toString() { 
        return "Employe{id=" + id + ", nom='" + nom + "', prenom='" + prenom + "', dept='" + nomDepartement + "', grade=" + grade + "}"; 
    }
}
interface IGestion<T> {
    void ajouterEmploye(T t); 
    boolean rechercherEmploye(String nom); 
    boolean rechercherEmploye(T t); 
    void supprimerEmploye(T t); 
    void displayEmploye(); 
    void trierEmployeParId(); 
    void trierEmployeParNomDepartementEtGrade(); 
}
class SocieteArrayList implements IGestion<Employe> {
    private ArrayList<Employe> employes = new ArrayList<>();
    
    public void ajouterEmploye(Employe e) { employes.add(e); System.out.println("Ajoute : " + e); }
    public boolean rechercherEmploye(String nom) { 
        return employes.stream().anyMatch(e -> e.getNom().equalsIgnoreCase(nom)); 
    }
    public boolean rechercherEmploye(Employe e) { return employes.contains(e); }
    public void supprimerEmploye(Employe e) { 
        if (employes.remove(e)) System.out.println("Supprim : " + e); 
        else System.out.println("Non trouve : " + e); 
    }
    public void displayEmploye() { employes.forEach(System.out::println); }
    public void trierEmployeParId() { 
        employes.sort(Comparator.comparingInt(Employe::getId)); 
        System.out.println("Trie par ID."); 
    }
    public void trierEmployeParNomdepartementEtGrade() { 
        employes.sort(Comparator.comparing(Employe::getNom).thenComparing(Employe::getNomDepartement).thenComparingInt(Employe::getGrade)); 
        System.out.println("Trie par Nom, departement, Grade."); 
    }
}
public class GestionEmployesApp {
    public static void main(String[] args) {
        SocieteArrayList societe = new SocieteArrayList();
        Employe e1 = new Employe(1, "Ali", "Ahmed", "IT", 3); 
        Employe e2 = new Employe(2, "Fatma", "Trabelsi", "HR", 4); 
        Employe e3 = new Employe(3, "Khaled", "Bouazizi", "Finance", 2);

        societe.ajouterEmploye(e1); societe.ajouterEmploye(e2); societe.ajouterEmploye(e3);
        System.out.println("Recherche nom 'Ali': " + societe.rechercherEmploye("Ali"));
        System.out.println("Recherche emploe e2: " + societe.rechercherEmploye(e2));
        societe.supprimerEmploye(e1); 
        societe.displayEmploye();
        societe.trierEmployeParId(); societe.displayEmploye();
        societe.trierEmployeParNomDepartementEtGrade(); societe.displayEmploye();
    }
}
