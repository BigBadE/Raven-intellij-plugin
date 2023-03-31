package bigbade.raven.ravenintellijplugin.parsing;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

public interface RavenTypes {
    IElementType COMMENT = new RavenElementType("COMMENT");

    class Factory {
        public static PsiElement createElement(ASTNode node) {
            return new ASTWrapperPsiElement(node);
        }
    }
}
