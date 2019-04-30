package com.viking.MySpringBoot.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Viking on 2019/4/30
 * 自定义实现驼峰式命名
 */
public class CamelNamingStrategy implements PhysicalNamingStrategy {

    private static final Map<String,String> ABBREVIATIONS = buildAbbreviationMap();
    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier;
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier;
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier;
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        /*final List<String> parts = splitAndReplace( identifier.getText() );
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                join( parts ),
                identifier.isQuoted()
        );*/
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                toCamelNaming(identifier),
                identifier.isQuoted());
    }

    private static Map<String, String> buildAbbreviationMap() {
        TreeMap<String,String> abbreviationMap = new TreeMap<> ( String.CASE_INSENSITIVE_ORDER );
        abbreviationMap.put( "account", "acct" );
        abbreviationMap.put( "number", "num" );
        return abbreviationMap;
    }

    private LinkedList<String> splitAndReplace(String name) {
        LinkedList<String> result = new LinkedList<>();
        for ( String part : StringUtils.splitByCharacterTypeCamelCase( name ) ) {
            if ( part == null || part.trim().isEmpty() ) {
                // skip null and space
                continue;
            }
            part = applyAbbreviationReplacement( part );
            result.add( part.toLowerCase( Locale.ROOT ) );
        }
        return result;
    }

    private String applyAbbreviationReplacement(String word) {
        if ( ABBREVIATIONS.containsKey( word ) ) {
            return ABBREVIATIONS.get( word );
        }

        return word;
    }

    private String join(List<String> parts) {
        boolean firstPass = true;
        String separator = "";
        StringBuilder joined = new StringBuilder();
        for ( String part : parts ) {
            joined.append( separator ).append( part );
            if ( firstPass ) {
                firstPass = false;
                separator = "_";
            }
        }
        return joined.toString();
    }


    /******************************************* 版本二 *************************************************************/
    private Identifier convert(Identifier identifier) {
        if (identifier == null || StringUtils.isBlank(identifier.getText())) {
            return identifier;
        }

        String regex = "([a-z])([A-Z])";
        String replacement = "$1_$2";
        String newName = identifier.getText().replaceAll(regex, replacement).toLowerCase();
        return Identifier.toIdentifier(newName);
    }
    /******************************************* 自定义实现 ***********************************************************/
    private String toCamelNaming(Identifier name){
        if (name==null||name.getText()==null||name.getText().length()==0) return name.getText();
        String regex = "([A-Z])";
        Pattern p = Pattern.compile(regex);
        String text = name.getText();
        Matcher m = p.matcher(text);
        while (m.find()){
            String s = m.group();
            text = text.replace(s,"_"+s.toLowerCase());
        }
//        return Identifier.toIdentifier(text);
        return text;
    }
}
