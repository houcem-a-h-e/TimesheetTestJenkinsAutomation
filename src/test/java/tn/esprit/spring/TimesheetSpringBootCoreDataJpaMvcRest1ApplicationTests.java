package tn.esprit.spring;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.logging.LogManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.Assert.assertEquals;
import org.springframework.boot.test.mock.mockito.MockBean;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;


import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;

@SpringBootTest
//@RunWith(SpringRunner.class)
public class TimesheetSpringBootCoreDataJpaMvcRest1ApplicationTests {
	@Autowired
	ITimesheetService iTimesheetService;
	@Autowired
	IEmployeService es;
	@Autowired
	IEntrepriseService  ents;
	
	@MockBean
	private ContratRepository cr;
	@MockBean
	private EmployeRepository er;
	@MockBean
	private TimesheetRepository tsr;
	@MockBean
	private MissionRepository mr;
	@MockBean
	private DepartementRepository dr;
	@MockBean
	private EntrepriseRepository entr;
 
	@Autowired
	private TestEntityManager tem;
	
	private static final Logger l = LoggerFactory.getLogger(TimesheetSpringBootCoreDataJpaMvcRest1ApplicationTests.class);
	
	
	@Test
	public void testAjouterTimesheet() {
		Employe emp=new Employe("Issaoui", "Wissem","wissem@gmail.com",true,Role.ADMINISTRATEUR);
		when(er.save(emp)).thenReturn(emp);
		l.info("test add contrat success");
		//es.ajouterEmploye(emp);
		tem.persist(emp);
		TimesheetPK tspk=new TimesheetPK();
		tspk.setIdEmploye(5);
		tspk.setIdMission(5);
		tspk.setDateDebut(new Date());
		tspk.setDateFin(new Date());
		Timesheet ts=new Timesheet();
		ts.setTimesheetPK(tspk);
		ts.setValide(true);
		when(tem.persist(ts)).thenReturn(ts);
		l.info("affichage ts: " + ts);
		assertEquals(ts.getTimesheetPK(), iTimesheetService.addTimeSheet(tspk,ts));
		l.info("timesheet ajouté avec succès");
	}
	
	
	/*@Component
	@Aspect
	public class PerformanceAspect{
	@Around("execution(* tn.esprit.spring.*.*.*(..))")
	public Object testExecutionTime(MethodInvocationProceedingJoinPoint projp) throws Throwable {
		long start=System.currentTimeMillis();
		Object object=projp.proceed();
		long elapsedTime = System.currentTimeMillis() - start;
		if (elapsedTime>3000) {
			l.info("Method execution time" + elapsedTime + "milliseconds");
return projp.getSignature();
		}  
		return null;
		
	}*/
	}

