
import is.iclt.icenlp.core.icemorphy.IceMorphyLexicons;
import is.iclt.icenlp.core.icetagger.IceTaggerLexicons;
import is.iclt.icenlp.core.tokenizer.Sentences;
import is.iclt.icenlp.core.utils.Lexicon;
import is.iclt.icenlp.facade.IceParserFacade;
import is.iclt.icenlp.facade.IceTaggerFacade;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
* This is a project in Natural Language Processing at the University of Reykjavik
*
* Authors: Carl Rosén and Edda Peturs
*
* */


public class Main {


    public static String parse(String sentences) throws IOException{
        IceParserFacade iceParserFacade = new IceParserFacade();
        String out = iceParserFacade.parse(sentences, true,false);
        return out;
    }

    public static String tag(String string) throws IOException{

        IceTaggerLexicons iceLexicons = new IceTaggerLexicons("src/icetagger/");
        IceMorphyLexicons morphyLexicons = new IceMorphyLexicons("src/icetagger/");
        Lexicon lexicon = new Lexicon();
        lexicon.load("src/lexicon.txt");
        String lex = "src/lexicon.txt";
        lexicon.load(lex);

        IceTaggerFacade iceTagger = new IceTaggerFacade(iceLexicons, morphyLexicons, lexicon);

        Sentences sentences = iceTagger.tag(string);
        String stringSent = sentences.toString();

        return stringSent;
    }

}
