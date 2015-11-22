package br.com.curiosity;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import br.com.curiosity.exception.base.CuriosityRuntimeException;
import br.com.curiosity.service.CuriosityService;
import br.com.curiosity.utils.type.IdentifyProbeEnum;

@Component
public final class App {

	@Autowired
	private  CuriosityService curiosityService;
	
	private Scanner scan;
	private String sizePlateaus;
	private String positionProbe1;
	private String intructionProbe1; 
	private String positionProbe2 ;
	private String intructionProbe2;
	
	
	public void console(){
		scan = new Scanner(System.in);
		System.out.println("Enter the plateau size: ex.: \"5 5\"");
		sizePlateaus = scan.nextLine();
		System.out.println("Enter the probe position: ex.: \"0 0 N\"");
		positionProbe1 = scan.nextLine();
		System.out.println("Enter the probe instructions: ex.: \"MMRML\"");
		intructionProbe1 = scan.nextLine();
		System.out.println("Enter the probe position: ex.: \"0 0 N\"");
		positionProbe2 = scan.nextLine();
		System.out.println("Enter the probe instructions: ex.: \"MMRML\"");
		intructionProbe2 = scan.nextLine();
		
	}
	
	public void executeCuriosity(){
		try {
			curiosityService.startPlateau(sizePlateaus)
			.startConfigProbe(IdentifyProbeEnum.POSEIDON, positionProbe1)
			.startConfigProbe(IdentifyProbeEnum.ATENAS, positionProbe2)
			.instructionProbe(IdentifyProbeEnum.POSEIDON, intructionProbe1)
			.instructionProbe(IdentifyProbeEnum.ATENAS, intructionProbe2)
			.run();
		} catch (CuriosityRuntimeException e) {
			System.err.println(e.getMessage());
			System.exit(0);
			
		}
	}
	
	public void statusCuriosity(){

		System.out.println(curiosityService.status(IdentifyProbeEnum.POSEIDON));
		System.out.println(curiosityService.status(IdentifyProbeEnum.ATENAS));
		
	}
	
	public static final void main(String... aArgs) {

		@SuppressWarnings("resource")
		App app = new AnnotationConfigApplicationContext("br.com.curiosity").getBean(App.class);
		
		app.console();
		app.executeCuriosity();
		app.statusCuriosity();		
	}
	
}
