package smpl.syntax;

public class ASTCaseBinding {

    private ASTExp logic;
    private ASTExp result;
    private ASTStmtSequence results;

    public ASTCaseBinding(ASTExp logic, ASTExp result) {

        this.logic = logic;
        this.result = result;
    }

    public ASTCaseBinding(ASTExp logic, ASTStmtSequence results)
    {
        this.logic = logic;
        this.results = results;
    }

    public ASTExp getLogic()
    {
        return logic;
    }

    public ASTExp getResult()
    {
        return this.result;
    }

    public ASTExp getResults()
    {
        return this.results;
    }

}