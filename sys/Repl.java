package smpl.sys;

import java_cup.runtime.*;
import java.io.*;
import java.util.*;
import smpl.semantics.*;
import smpl.syntax.*;
import smpl.values.*;

public class Repl {

    public static final String PROMPT = "Eval>";

    public static void main(String args[]) {
    	ArrayList<String> files = new ArrayList<String>();
    	for (int i=0; i<args.length;i++){
    			files.add(args[i]);
    	}
    	if (args.length != 0){
    		visitFiles(files);
    	}
	repl(new Environment());

    }

    public static void repl(Environment env){
	InputStreamReader reader = new InputStreamReader(System.in);
	while (true) {
	    parseEvalShow(reader, env);
	}
    }


    public static void visitFiles(ArrayList<String> fileNames) {
        // Treat all other command line arguments as files to be read and evaluated
        FileReader freader;
        for (String file : fileNames) {
            try {
                System.out.println("Reading from: " + file + "...");
                freader = new FileReader(new File(file));
                parseEvalShow(freader,new Environment());
                System.out.println("Done! Press ENTER to continue");
                System.in.read();
            } catch (FileNotFoundException fnfe) {
                System.err.println(fnfe.getMessage());
                System.err.println("Skipping it");
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
        }
    }

    public static void parseEvalShow(Reader reader,
				     Environment env) {
	SmplParser parser;
	ASTProgram program = null;
	Evaluator interp = new Evaluator();
	System.out.print(PROMPT);
	try {
	    parser = new SmplParser(new SmplLexer(reader));
	    program = (ASTProgram) parser.parse().value;
	} catch (Exception e) {
	    System.out.println("Parse Error: " + e.getMessage());
	}

	if (program != null)
	    try {
		Object result;
		result = program.visit(interp, env);
		System.out.println("\nResult: " + result);
	    } catch (Exception e) {
		System.out.println(e.getMessage());
	    }
    }

}
