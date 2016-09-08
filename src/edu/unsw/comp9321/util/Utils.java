package edu.unsw.comp9321.util;

import edu.unsw.comp9321.Constant;
import edu.unsw.comp9321.entity.Article;
import edu.unsw.comp9321.entity.Page;
import edu.unsw.comp9321.entity.Paginator;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Langley on 8/27/16.
 */
public class Utils {

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static Page getPage(List<Article> articles, int pageNumber) {
        Paginator paginator = new Paginator(articles, Constant.PER_PAGE);
        Page page = paginator.getPage(pageNumber);
        return page;
    }

}
