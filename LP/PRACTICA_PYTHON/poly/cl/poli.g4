grammar poli;

/** Sintàxis */

root:assignColor EOF
        | consultaPOLY EOF
        | operacionsPOLY2 EOF
        | operacionsPOLY1 EOF
        | printMSG EOF
        | newPoly EOF
        | EOF
        ;

poly: OPENEXP (polyID|vertexList|operacionsPOLY1|operacionsPOLY2) CLSEEXP
        |(polyID|vertexList|operacionsPOLY1|operacionsPOLY2)
        ;

vertex : (INT|FLOAT) (INT|FLOAT);

vertexList: OPENLIST vertex* CLSELIST;
colorList: OPENCOLOR (INT|FLOAT) (INT|FLOAT) (INT|FLOAT) CLSECOLOR;

newPoly:polyID ASSIGN poly;
assignColor: CMDCOLOR polyID ',' colorList;

consultaPOLY: CMDAREA poly
                | CMDPERIMETRE poly
                | CMDVERTICES poly
                | CMDCENTROID poly
                | CMDPRINT poly
                | CMDEQ poly COMA poly
                | CMDINSIDE poly COMA poly
                | CMDDRAW TXT COMA poly (COMA poly)* 
                ;

printMSG : CMDPRINT TXT;

operacionsPOLY1: OPERATORBOUNDINGBOX poly
                    |OPERATORRANDOMPOLY INT
                    ;

operacionsPOLY2: polyID OPERATORUNION poly
                |polyID OPERATORINTER poly
                ;
polyID: ID;

/** definicions lexic */

OPENLIST : '[';
CLSELIST :  ']';

OPENCOLOR: '{';
CLSECOLOR: '}';

OPENEXP: '(';
CLSEEXP: ')';

COMA: ',';

ASSIGN : ':=';

/**COMANDES */
CMDCOLOR: 'color';
CMDPRINT: 'print';

CMDAREA : 'area';
CMDPERIMETRE: 'perimeter';
CMDVERTICES : 'vertices';
CMDCENTROID : 'centroid';

CMDINSIDE : 'inside';
CMDEQ : 'equal';
CMDDRAW: 'draw';

/**OPERADORS */

OPERATORUNION: '+';
OPERATORINTER: '*';
OPERATORBOUNDINGBOX: '#';
OPERATORRANDOMPOLY: '!';

/**DEFINICIONS GENERALS */

TXT: '"'.*'"';
ID : [a-zA-Z_]+[0-9]*[a-zA-Z_]*;
INT: [0-9]+;
FLOAT: [0-9]+'.'[0-9]+;
ENDLINE: '\n';

COMMENT : '//'.*-> skip;
WS : [ \t\r]+ -> skip;