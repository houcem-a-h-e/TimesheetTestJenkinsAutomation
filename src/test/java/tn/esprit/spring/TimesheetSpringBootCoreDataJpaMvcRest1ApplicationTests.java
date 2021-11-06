package tn.esprit.spring;


import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
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
@RunWith(SpringRunner.class)
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

	
	private static final Logger l = LogManager.getLogger(TimesheetSpringBootCoreDataJpaMvcRest1ApplicationTests.class);

	
	/*@Test
	public void ajouterMission() {
		Mission mission= new Mission("externe","duréee de 3 mois");
		
		int miss=iTimesheetService.ajouterMission(mission);
		 assertThat(miss).isEqualTo(7);
		
	}*/
	
	@Test
	public void testAjouterTimesheet() {
		Employe emp=new Employe("Issaoui", "Wissem","wissem@gmail.com",true,Role.ADMINISTRATEUR);
		when(er.save(emp)).thenReturn(emp);
		l.info("test add contrat success");
		es.ajouterEmploye(emp);
		TimesheetPK tspk=new TimesheetPK();
		tspk.setIdEmploye(5);
		tspk.setIdMission(5);
		tspk.setDateDebut(new Date());
		tspk.setDateFin(new Date());
		Timesheet ts=new Timesheet();
		ts.setTimesheetPK(tspk);
		ts.setValide(true);
		when(tsr.save(ts)).thenReturn(ts);
		l.info("affichage ts: " + ts);
		assertEquals((ts).getTimesheetPK(), iTimesheetService.addTimeSheet(tspk,ts));
		l.info("timesheet ajouté avec succès");
	}
	
	@Component
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
		
	}
	}
}
