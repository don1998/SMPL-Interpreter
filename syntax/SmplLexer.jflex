/* Specification for smpl tokens */

// user customisations

package smpl.syntax;
import java_cup.runtime.*;
import smpl.sys.SmplException;
import smpl.sys.SmplLexerException;

// JFlex directives
    
%%

%cup
%public

%class SmplLexer
%throws SmplException

%type java_cup.runtime.Symbol

%eofval{
	return new Symbol(sym.EOF);
%eofval}

%eofclose false

%state STRING
%state YYCOMMENT

%char
%column
%line

%{
    private Symbol mkSymbol(int id) {
        return new Symbol(id, yyline, yycolumn);
    }

    private Symbol mkSymbol(int id, Object val) {
        return new Symbol(id, yyline, yycolumn, val);
    }

    public int getChar() {
	return yychar + 1;
    }

    public int getColumn() {
    	return yycolumn + 1;
    }

    public int getLine() {
	return yyline + 1;
    }

    public String getText() {
	return yytext();
    }

    public String getHex(){
        return yytext().substring(2);
    }

    public char getUniChar(){
        return Character.toChars(Integer.parseInt(getHex(),16))[0];
    }

    public String getBinary(){
        return yytext().substring(2);
    }

    public String getStringConst(){
        return yytext().substring(1, yytext().length()-1);
    }
%}

nl = [\n\r]

cc = ([\b\f]|{nl})

ws = {cc}|[\t ]

digit = [+-]?[0-9]

rdigit = [+-]?([0-9]*[.])?[0-9]+

hexdigit = [0-9a-fA-F]{4}

binarydigit = #b[01]+

alpha = [_a-zA-Z?]

strconstant = \"(.[^\"]*)\"

escaped = [\"\\t\"] | [\"\\n\"] | [\\] 

charconstant = ['][ -~]['] | '\\?{escaped}'

alternatechar = [#][c][a-zA-Z\\_\n\t]{1,2}

unicharconstant = [#][u][0-9a-fA-F]{4}

hexint = [#][x]{hexdigit}

variable = [_a-zA-Z0-9\-+\%*\^?]+

alphnum = {digit}|{alpha}


%%

<YYINITIAL>	{nl}	{
			 //skip newline
			}
<YYINITIAL>	{ws}	{
			 // skip whitespace
			}


<YYINITIAL>	"/*"	{
			 yybegin (YYCOMMENT);
			}

<YYINITIAL>	"//".*	{
			 // skip line comments
			}

<YYINITIAL> {
    "+"			{return mkSymbol(sym.PLUS);}
    "-"			{return mkSymbol(sym.MINUS);}
    "*"			{return mkSymbol(sym.MUL);}
    "/"			{return mkSymbol(sym.DIV);}
    "%"			{return mkSymbol(sym.MOD);}
    "^"         {return mkSymbol(sym.POW);}

    "("			{return mkSymbol(sym.LPAREN);}
    ")"			{return mkSymbol(sym.RPAREN);}
    "{"         {return mkSymbol(sym.LCURLY);}
    "}"         {return mkSymbol(sym.RCURLY);}
    "["         {return mkSymbol(sym.LBRACE);}
    "]"         {return mkSymbol(sym.RBRACE);}
    "="         {return mkSymbol(sym.EQUAL);}


/*I added this region*/

    ","			{return mkSymbol(sym.COMMA);}
"<"|">"|"<="|">="|"="|"!="  { return new Symbol(sym.COMPARE, yytext()); }

    "and"		{return mkSymbol(sym.AND);}
    "or"		{return mkSymbol(sym.OR);}
    "not"		{return mkSymbol(sym.NOT);}


    "&"         {return mkSymbol(sym.BITAND);}
    "|"         {return mkSymbol(sym.BITOR);}
    "~"         {return mkSymbol(sym.BITNEG);}
    "@"         {return mkSymbol(sym.CONCAT);}
    ":="        {return mkSymbol(sym.ASSIGN);}
    ":"         {return mkSymbol(sym.COLON);}
    ";"         {return mkSymbol(sym.SEMI);}
    "."         {return mkSymbol(sym.PERIOD);}


    "#t"        {return mkSymbol(sym.TRUE);}
    "#f"        {return mkSymbol(sym.FALSE);}
    "#e"        {return mkSymbol(sym.EMPTY_LIST);}

    "proc"      {return mkSymbol(sym.PROC);}
    "call"      {return mkSymbol(sym.CALL);}
    "lazy"      {return mkSymbol(sym.LAZY);}
    "let"       {return mkSymbol(sym.LET);}
    "def"       {return mkSymbol(sym.DEF);}
    "if"        {return mkSymbol(sym.IF);}
    "then"      {return mkSymbol(sym.THEN);}
    "else"      {return mkSymbol(sym.ELSE);}
    "case"      {return mkSymbol(sym.CASE);}
    "print"     {return mkSymbol(sym.PRINT);}
    "println"   {return mkSymbol(sym.PRINTLN);}
    "read"      {return mkSymbol(sym.READ);}
    "readint"   {return mkSymbol(sym.READINT);} 
    "while"     {return mkSymbol(sym.WHILE);}
    "redefine"  {return mkSymbol(sym.REDEFINE);}
    


/*I added this region*/

    {hexint}    {return new Symbol(sym.INTEGER, Integer.parseInt(getHex(), 16));}

    {binarydigit} {return new Symbol(sym.INTEGER, Integer.parseInt(getBinary(), 2));}

    {unicharconstant} {return mkSymbol(sym.UNICHAR, getUniChar());}

    {alternatechar}    {return mkSymbol(sym.ALTCHAR, yytext().substring(2).toString());}

    {digit}+        {
             // INTEGER
                 return mkSymbol(sym.INTEGER, 
                         new Integer(yytext()));
                }
    {rdigit} 		{
			 // DOUBLE
	       		 return mkSymbol(sym.REAL, 
			 	         new Double(yytext()));
	       		}

    {variable}   {
    		      	 // IDENTIFIERS
	       		 return mkSymbol(sym.VARIABLE, yytext());
	       		}

    {charconstant}   {
                     // CHARS
                 return mkSymbol(sym.CHAR, yytext());
                }

    {strconstant} {return new Symbol(sym.STRING, getStringConst());}
}

<YYCOMMENT>	.|{nl} {
			// skip block comments
		      }
<YYCOMMENT>	"*/"	{
			 yybegin (YYINITIAL);
			}

<YYINITIAL>   .		{ // Unknown token (leave this in the last position)
    			  throw new SmplLexerException(yytext(), getLine(),
							  getColumn());
    			}
