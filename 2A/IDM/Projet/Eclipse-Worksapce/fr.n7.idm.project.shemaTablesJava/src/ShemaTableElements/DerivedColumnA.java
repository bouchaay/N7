package ShemaTableElements;

public class DerivedColumnA extends ColumnA {

    /** L'algorithme qui a permis de créer la colonne */
    AlgorithmA algorithm;

    /**
     * Constructeur de la classe DerivedColumn (LA colonne est dérivé d'autres colonnes à partir d'un algortihme, donc c'est un Output d'algorithme)
     * @param output L'output dont la vient
     * @param algorithm L'algorithme qui a permis de créer la colonne
     */
    public DerivedColumnA(AlgorithmA algorithm) {
        super(algorithm.getOutput().getName(), algorithm.getOutput().getId(), algorithm.getOutput().getDataType(), algorithm.getOutput().getConstraintFile());
        this.algorithm = algorithm;
    }
    
    public AlgorithmA getAlgorithm() {
    	return this.algorithm;
    }
}
