package is;

import is.iclt.icenlp.core.icemorphy.IceMorphyLexicons;
import is.iclt.icenlp.core.icetagger.IceTaggerLexicons;
import is.iclt.icenlp.core.tokenizer.Sentences;
import is.iclt.icenlp.core.utils.Lexicon;
import is.iclt.icenlp.facade.IceParserFacade;
import is.iclt.icenlp.facade.IceTaggerFacade;
import java.io.*;

/*
* This is a project in Natural Language Processing at the University of Reykjavik
*
* Authors: Carl Rosén and Edda Peturs
*
* */


public class NLPHandler {


    public static String parse(String sentences) throws IOException{
        IceParserFacade iceParserFacade = new IceParserFacade();
        String out = iceParserFacade.parse(sentences, true,false);
        return out;
    }

    public static String tag(String string) throws IOException{

        System.out.println("Before lexicons");
        IceTaggerLexicons iceLexicons = new IceTaggerLexicons("src/is/icetagger/");
        System.out.println("Got the Lexicon1");
        IceMorphyLexicons morphyLexicons = new IceMorphyLexicons("src/is/icetagger/");
        Lexicon lexicon = new Lexicon();
        lexicon.load("src/is/lexicon.txt");
        String lex = "src/is/lexicon.txt";
        lexicon.load(lex);

        IceTaggerFacade iceTagger = new IceTaggerFacade(iceLexicons, morphyLexicons, lexicon);

        Sentences sentences = iceTagger.tag(string);
        String stringSent = sentences.toString();
        return stringSent;
    }

}
