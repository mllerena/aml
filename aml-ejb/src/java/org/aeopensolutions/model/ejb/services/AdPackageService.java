package org.aeopensolutions.model.ejb.services;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.el.FunctionMapper;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.sql.DataSource;

import org.aeopensolutions.model.entities.AbstractEntityModel;
import org.aeopensolutions.model.enums.YesNo;


@Stateless
public class AdPackageService {   

    private static final Logger LOGGER = Logger.getLogger(AdPackageService.class.getName());
    
    @PersistenceContext(unitName = "aml-ejbPU")
	private EntityManager em;

    public AdPackageService() {
    	
    }
    
    public String nextSequence(AbstractEntityModel e){
    	Table annotation = e.getClass().getAnnotation(Table.class);
		String id = null;
		return id;
    }
    
    public String sequence(String sequenceName, String adClientId){
    	
		//System.out.println("dsPharma conexion: "+dsPharma);	
    	String sequenceDoc = null;
    	
    	try{
    		
    	
    	
    	System.out.println("sequence sequenceName: "+sequenceName);
    	System.out.println("sequence adClientId: "+adClientId);
    	
    	StoredProcedureQuery sp = em.createStoredProcedureQuery("ad_sequence_doc");
        sp.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter(3, Character.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter(4, String.class, ParameterMode.OUT);
        
        sp.setParameter(1, sequenceName);
        sp.setParameter(2, adClientId);
        sp.setParameter(3, 'Y');
        
        sp.execute();
        
        sequenceDoc = (String) sp.getOutputParameterValue(4);
    	
        System.out.println("sequenceDoc: "+sequenceDoc);
        
        
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	
    	return sequenceDoc;
    }
    

}
