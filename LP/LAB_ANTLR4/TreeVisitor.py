if __name__ is not None and "." in __name__:
    from .ExprParser import ExprParser
    from .ExprVisitor import ExprVisitor
else:
    from ExprParser import ExprParser
    from ExprVisitor import ExprVisitor
    
class TreeVisitor(ExprVisitor):
    def __init__(self):
        self.nivell = 0
    def visitExpr(self, ctx:ExprParser.ExprContext):
        if ctx.getChildCount() == 1:
            n = next(ctx.getChildren())
            print("  " * self.nivell +
                  ExprParser.symbolicNames[n.getSymbol().type] +
                  '(' +n.getText() + ')')
            self.nivell -= 1
        elif ctx.getChildCount() == 3:
            l = [n for n in ctx.getChildren()]
            operador = ExprParser.symbolicNames[l[1].getSymbol().type]
            print('  ' *  self.nivell + operador)
            self.nivell += 1
            self.visit(ctx.expr(0))
            self.nivell += 1
            self.visit(ctx.expr(1))
            self.nivell -= 1

class EvalVisitor(ExprVisitor):
    
    def visitRoot(self, ctx:ExprParser.RootContext):
        n = next(ctx.getChildren())        
        print(self.visit(n))
        
    def visitExpr(self, ctx:ExprParser.ExprContext):
        l = [n for n in ctx.getChildren()]        
        
        if len(l) == 1:
            return int(l[0].getText())

        elif len(l) == 3:
            operador = l[1].getSymbol().type
    
            if operador == ExprParser.MES:
                return self.visit(l[0]) + self.visit(l[2])            
            elif operador == ExprParser.RESTA:
                return self.visit(l[0]) - self.visit(l[2])
            elif operador == ExprParser.MULT:
                return self.visit(l[0]) * self.visit(l[2])
            elif operador == ExprParser.DIVISIO:
                return self.visit(l[0]) / self.visit(l[2])
            elif operador == ExprParser.POW:
                return self.visit(l[0]) ** self.visit(l[2])
            else:
                print("no defined!")

    def visitNewVar(self, ctx:ExprParser.ExprContext):
        #  <id> := exp
        l = [n for n in ctx.getChildren()]
        print('#identificador:', l[0].getText())
        print('#calc expr:', l[2].getText())
        r = self.visit(l[2])
        return r