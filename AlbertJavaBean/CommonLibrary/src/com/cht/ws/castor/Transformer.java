package com.cht.ws.castor;

import java.io.ByteArrayInputStream;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.InputSource;

import com.cht.dto.ws.*;

public class Transformer {

	
	public static synchronized UserDTO describeSubscriber(String xml) {
		
		Mapping mapping = new Mapping();
		String mappingFile = "mapping/describeSubscriber-mapping.xml";
		UserDTO user = null;
		
		try {
			mapping.loadMapping(new InputSource(Transformer.class.getResourceAsStream(mappingFile)));
			Unmarshaller umar = new Unmarshaller(mapping);
			user = (UserDTO)umar.unmarshal(new InputSource(new ByteArrayInputStream(xml.getBytes())));
		} catch (MarshalException e) {
			
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		} catch (MappingException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
public static synchronized DeviceDTO describeClient(String xml) {
		
		Mapping mapping = new Mapping();
		String mappingFile = "mapping/describeClient-mapping.xml";
		DeviceDTO device = null;
		
		try {
			mapping.loadMapping(new InputSource(Transformer.class.getResourceAsStream(mappingFile)));
			Unmarshaller umar = new Unmarshaller(mapping);
			device = (DeviceDTO)umar.unmarshal(new InputSource(new ByteArrayInputStream(xml.getBytes())));
		} catch (MarshalException e) {
			
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		} catch (MappingException e) {
			e.printStackTrace();
		}
		
		return device;
	}


public static synchronized ListDTO listClientSubscriptions(String xml) {
	
	Mapping mapping = new Mapping();
	String mappingFile = "mapping/listClientSubscriptions-mapping.xml";
	ListDTO dto = null;
	
	try {
		mapping.loadMapping(new InputSource(Transformer.class.getResourceAsStream(mappingFile)));
		Unmarshaller umar = new Unmarshaller(mapping);
		dto = (ListDTO)umar.unmarshal(new InputSource(new ByteArrayInputStream(xml.getBytes())));
	} catch (MarshalException e) {
		e.printStackTrace();
	} catch (ValidationException e) {
		e.printStackTrace();
	} catch (MappingException e) {
		e.printStackTrace();
	}
	
	return dto;
}
public static synchronized UserGroupsDTO listSubscriberGroups(String xml) {
	
	Mapping mapping = new Mapping();
	String mappingFile = "mapping/listSubscriberGroups-mapping.xml";
	UserGroupsDTO dto = null;
	
	try {
		mapping.loadMapping(new InputSource(Transformer.class.getResourceAsStream(mappingFile)));
		Unmarshaller umar = new Unmarshaller(mapping);
		dto = (UserGroupsDTO)umar.unmarshal(new InputSource(new ByteArrayInputStream(xml.getBytes())));
	} catch (MarshalException e) {
		
		e.printStackTrace();
	} catch (ValidationException e) {
		e.printStackTrace();
	} catch (MappingException e) {
		e.printStackTrace();
	}
	
	return dto;
}

public static synchronized ListDTO listFeedApps(String xml) {
	
	Mapping mapping = new Mapping();
	String mappingFile = "mapping/listFeedApps-mapping.xml";
	ListDTO dto = null;
	
	try {
		System.out.println("Transformer xml:"+xml);
		mapping.loadMapping(new InputSource(Transformer.class.getResourceAsStream(mappingFile)));
		Unmarshaller umar = new Unmarshaller(mapping);
		dto = (ListDTO)umar.unmarshal(new InputSource(new ByteArrayInputStream(xml.getBytes())));
	} catch (MarshalException e) {
		
		e.printStackTrace();
	} catch (ValidationException e) {
		e.printStackTrace();
	} catch (MappingException e) {
		e.printStackTrace();
	}
	
	return dto;
}

public static synchronized ListDTO listCategories(String xml) {
	
	Mapping mapping = new Mapping();
	String mappingFile = "mapping/listCategories-mapping.xml";
	ListDTO dto = null;
	
	try {
		System.out.println("Transformer xml:"+xml);
		mapping.loadMapping(new InputSource(Transformer.class.getResourceAsStream(mappingFile)));
		Unmarshaller umar = new Unmarshaller(mapping);
		dto = (ListDTO)umar.unmarshal(new InputSource(new ByteArrayInputStream(xml.getBytes())));
	} catch (MarshalException e) {
		
		e.printStackTrace();
	} catch (ValidationException e) {
		e.printStackTrace();
	} catch (MappingException e) {
		e.printStackTrace();
	}
	
	return dto;
}
/**
 *  Lu
 */

public static synchronized ListDTO listSubscriberAcl(String xml) {

	Mapping mapping = new Mapping();
	String mappingFile = "mapping/listSubscriberAcl-mapping.xml";
	ListDTO dto = null;

	try {
		mapping.loadMapping(new InputSource(Transformer.class
				.getResourceAsStream(mappingFile)));
		Unmarshaller umar = new Unmarshaller(mapping);
		dto = (ListDTO) umar.unmarshal(new InputSource(
				new ByteArrayInputStream(xml.getBytes())));
	} catch (MarshalException e) {

		e.printStackTrace();
	} catch (ValidationException e) {
		e.printStackTrace();
	} catch (MappingException e) {
		e.printStackTrace();
	}

	return dto;
}
/**
 *  Lu
 */

public static synchronized FeedappDTO describeFeedApp(String xml) {

	Mapping mapping = new Mapping();
	String mappingFile = "mapping/FeedApp-mapping.xml";
	FeedappDTO dto = null;

	try {
		mapping.loadMapping(new InputSource(Transformer.class
				.getResourceAsStream(mappingFile)));
		Unmarshaller umar = new Unmarshaller(mapping);
		dto = (FeedappDTO) umar.unmarshal(new InputSource(
				new ByteArrayInputStream(xml.getBytes())));
	} catch (MarshalException e) {
		System.out.println("Transformer.describeFeedApp MarshalException error:"+e.toString());
	} catch (ValidationException e) {
		System.out.println("Transformer.describeFeedApp ValidationException error:"+e.toString());
	} catch (MappingException e) {
		System.out.println("Transformer.describeFeedApp MappingException error:"+e.toString());
	}

	return dto;
}
/**
 *  Lu
 */

public static synchronized PackageDTO describePackage(String xml) {
	Mapping mapping = new Mapping();
	String mappingFile = "mapping/Package-mapping.xml";
	PackageDTO dto = null;

	try {
		mapping.loadMapping(new InputSource(Transformer.class
				.getResourceAsStream(mappingFile)));
		Unmarshaller umar = new Unmarshaller(mapping);
		dto = (PackageDTO) umar.unmarshal(new InputSource(
				new ByteArrayInputStream(xml.getBytes())));
	} catch (MarshalException e) {

		e.printStackTrace();
	} catch (ValidationException e) {
		e.printStackTrace();
	} catch (MappingException e) {
		e.printStackTrace();
	}

	return dto;
}
/**
 *  Lu
 */

public static synchronized ListDTO listSubscriberClients(String xml) {

	Mapping mapping = new Mapping();
	String mappingFile = "mapping/listSubscriberClients-mapping.xml";
	ListDTO dto = null;

	try {
		mapping.loadMapping(new InputSource(Transformer.class
				.getResourceAsStream(mappingFile)));
		Unmarshaller umar = new Unmarshaller(mapping);
		dto = (ListDTO) umar.unmarshal(new InputSource(
				new ByteArrayInputStream(xml.getBytes())));
	} catch (MarshalException e) {

		e.printStackTrace();
	} catch (ValidationException e) {
		e.printStackTrace();
	} catch (MappingException e) {
		e.printStackTrace();
	}

	return dto;
}

/**
 *  Lu
 */
public static synchronized CategoryDTO describeCategory(String xml) {
	Mapping mapping = new Mapping();
	String mappingFile = "mapping/Category-mapping.xml";
	CategoryDTO dto = null;

	try {
		mapping.loadMapping(new InputSource(Transformer.class
				.getResourceAsStream(mappingFile)));
		Unmarshaller umar = new Unmarshaller(mapping);
		dto = (CategoryDTO) umar.unmarshal(new InputSource(
				new ByteArrayInputStream(xml.getBytes())));
	} catch (MarshalException e) {
		System.out.println("Transformer.describeCategory MarshalException error:"+e.toString());
	} catch (ValidationException e) {
		System.out.println("Transformer.describeCategory ValidationException error:"+e.toString());
	} catch (MappingException e) {
		System.out.println("Transformer.describeCategory MappingException error:"+e.toString());
	}

	return dto;
}


/**
 *  Lu
 */
public static synchronized ListDTO listCategoryAcl(String xml) {
	Mapping mapping = new Mapping();
	String mappingFile = "mapping/CategoryAcl-mapping.xml";
	ListDTO dto = null;

	try {
		mapping.loadMapping(new InputSource(Transformer.class
				.getResourceAsStream(mappingFile)));
		Unmarshaller umar = new Unmarshaller(mapping);
		dto = (ListDTO) umar.unmarshal(new InputSource(
				new ByteArrayInputStream(xml.getBytes())));
	} catch (MarshalException e) {
		System.out.println("Transformer.describeCategory MarshalException error:"+e.toString());
	} catch (ValidationException e) {
		System.out.println("Transformer.describeCategory ValidationException error:"+e.toString());
	} catch (MappingException e) {
		System.out.println("Transformer.describeCategory MappingException error:"+e.toString());
	}

	return dto;
}

/**
 *  Lu
 */
public static synchronized ListDTO listSubscriberGroupAcl(String xml) {
	Mapping mapping = new Mapping();
	String mappingFile = "mapping/SubscriberGroupAcl-mapping.xml";
	ListDTO dto = null;

	try {
		mapping.loadMapping(new InputSource(Transformer.class
				.getResourceAsStream(mappingFile)));
		Unmarshaller umar = new Unmarshaller(mapping);
		dto = (ListDTO) umar.unmarshal(new InputSource(
				new ByteArrayInputStream(xml.getBytes())));
	} catch (MarshalException e) {
		System.out.println("Transformer.describeCategory MarshalException error:"+e.toString());
	} catch (ValidationException e) {
		System.out.println("Transformer.describeCategory ValidationException error:"+e.toString());
	} catch (MappingException e) {
		System.out.println("Transformer.describeCategory MappingException error:"+e.toString());
	}

	return dto;
}

/**
 *  Lu
 */
public static synchronized ListDTO listPackageFeedApps(String xml) {
	Mapping mapping = new Mapping();
	String mappingFile = "mapping/PackageFeedapp-mapping.xml";
	ListDTO dto = null;
	try {
		mapping.loadMapping(new InputSource(Transformer.class
				.getResourceAsStream(mappingFile)));
		Unmarshaller umar = new Unmarshaller(mapping);
		dto = (ListDTO) umar.unmarshal(new InputSource(
				new ByteArrayInputStream(xml.getBytes())));
	} catch (MarshalException e) {
		System.out.println("Transformer.listPackageFeedApps MarshalException error:"+e.toString());
	} catch (ValidationException e) {
		System.out.println("Transformer.listPackageFeedApps ValidationException error:"+e.toString());
	} catch (MappingException e) {
		System.out.println("Transformer.listPackageFeedApps MappingException error:"+e.toString());
	}

	return dto;
}

/**
 *  Lu
 */
public static synchronized ListDTO listPackageSubscriptions(String xml) {
	Mapping mapping = new Mapping();
	String mappingFile = "mapping/PackageSubscription-mapping.xml";
	ListDTO dto = null;
	try {
		mapping.loadMapping(new InputSource(Transformer.class
				.getResourceAsStream(mappingFile)));
		Unmarshaller umar = new Unmarshaller(mapping);
		dto = (ListDTO) umar.unmarshal(new InputSource(
				new ByteArrayInputStream(xml.getBytes())));
	} catch (MarshalException e) {
		System.out.println("Transformer.listPackageSubscriptions MarshalException error:"+e.toString());
	} catch (ValidationException e) {
		System.out.println("Transformer.listPackageSubscriptions ValidationException error:"+e.toString());
	} catch (MappingException e) {
		System.out.println("Transformer.listPackageSubscriptions MappingException error:"+e.toString());
	}

	return dto;
}


}
