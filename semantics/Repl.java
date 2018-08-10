package smpl.sys;

import smpl.semantics.*;
import smpl.values.*;
import smpl.syntax.*;

import java_cup.runtime.*;
import java.io.*;

public class Repl {

    public static final String PROMPT = " Eval>";

    protected static Evaluator evaluator;

    public static void main(String args[]) {
    	if (args.length == 0) {
        repl(System.in, Environment.makeGlobalEnv());
      }else {
        for (int x = 0; x < args.length; x++) {
          try {
            System.out.println("\n" + args[x] + "\n======================================\n");
            parseEvalShow(new FileReader(new File(args[x])), Environment.makeGlobalEnv());
          } catch(FileNotFoundException e) {
            System.out.println("Could not find file " + args[0]);
          }
        }
      }
    }

    public static void repl(InputStream is, Environment env) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      while (true) {
        try {
            System.out.print(PROMPT);
            StringBuffer input = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
              input.append("\n");
              input.append(line);
              line = reader.readLine();
            }
            StringReader r = new StringReader(new String(input));
            parseEvalShow(r, env);
      } catch (Exception ex) {
          System.out.println("Error, Exiting");
      }
    }
    }

    public static void parseEvalShow(Reader r, Environment env) {
		SmplParser parser;
		ASTProgram program = null;
		Evaluator interp = new Evaluator();
	    try {
			parser = new SmplParser(new SmplLexer(r));
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
