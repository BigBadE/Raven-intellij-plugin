package bigbade.raven.ravenintellijplugin.parsing;

import bigbade.raven.ravenintellijplugin.natives.NativeParserRunner;
import bigbade.raven.ravenintellijplugin.natives.NativeUtils;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerPosition;
import com.intellij.psi.tree.IElementType;
import org.bouncycastle.math.raw.Nat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Native;

public class RavenLexer extends Lexer {
    public String buffer;
    public long reference;
    public int start;
    public int end;

    @Override
    public void start(@NotNull CharSequence inBuffer, int startOffset, int endOffset, int initialState) {
        NativeUtils.setup();
        buffer = inBuffer.toString();
        start = startOffset;
        end = endOffset;
        reference = NativeParserRunner.start(buffer, startOffset, endOffset, initialState);
    }

    @Override
    public int getState() {
        return NativeParserRunner.getState(reference);
    }

    @Override
    public @Nullable IElementType getTokenType() {
        switch (NativeParserRunner.getTokenType(reference)) {

        }
        return null;
    }

    @Override
    public int getTokenStart() {
        return NativeParserRunner.getTokenStart(reference);
    }

    @Override
    public int getTokenEnd() {
        return NativeParserRunner.getTokenEnd(reference);
    }

    @Override
    public void advance() {
        NativeParserRunner.advance(reference);
    }

    @Override
    public @NotNull LexerPosition getCurrentPosition() {
        return new RavenLexerPosition(NativeParserRunner.getCurrentPosition(reference));
    }

    @Override
    public void restore(@NotNull LexerPosition position) {
        NativeParserRunner.restore(((RavenLexerPosition) position).getId(), reference);
    }

    @Override
    public @NotNull CharSequence getBufferSequence() {
        return buffer;
    }

    @Override
    public int getBufferEnd() {
        return end;
    }

    public static class RavenLexerPosition implements LexerPosition {
        private long id;

        public RavenLexerPosition(long foundId) {
            id = foundId;
        }

        public long getId() {
            return id;
        }

        @Override
        public int getOffset() {
            return NativeParserRunner.getPositionOffset(id);
        }

        @Override
        public int getState() {
            return NativeParserRunner.getPositionState(id);
        }
    }
}
