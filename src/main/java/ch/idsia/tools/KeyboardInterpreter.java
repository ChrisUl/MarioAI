package ch.idsia.tools;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ch.idsia.mario.engine.MarioComponent;

/**
 * KeyListener for executing actions in the given environment based on user input
 * @author rgu
 *
 */
public class KeyboardInterpreter implements KeyListener{
	
	private ToolsConfigurator configurator;

	public KeyboardInterpreter(ToolsConfigurator configurator) {
		configurator.addKeyListener(this);
		this.configurator=configurator;
	}
	
	@Override
	public void keyTyped(KeyEvent e) { //not used
	}

	@Override
	public void keyPressed(KeyEvent e) { //not used
	}

	@Override
	public void keyReleased(KeyEvent e) {
	//System.out.println(e.getKeyChar()+" "+e.getKeyCode()); //debug
		final MarioComponent actualComponent=configurator.getControlledComponent();
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_P:
			actualComponent.togglePaused();
			break;
		case KeyEvent.VK_I:
			actualComponent.showMarioViewAsAscii();
			break;
		case KeyEvent.VK_O:
			actualComponent.toggleDebugView();
			break;
		case KeyEvent.VK_T:
			actualComponent.performTick();
			break;
		case KeyEvent.VK_H:
			actualComponent.swapAgent();
			break;
		case KeyEvent.VK_MINUS:
		case 109:
			actualComponent.resizeView(actualComponent.getActualDimension().width-32, actualComponent.getActualDimension().height-24);
			break;
		case KeyEvent.VK_PLUS:
		case 107:
			actualComponent.resizeView(actualComponent.getActualDimension().width+32, actualComponent.getActualDimension().height+24);
		break;
		case 520:
		case 106:	
			actualComponent.resizeView(actualComponent.getInitialDimension().height, actualComponent.getInitialDimension().width);
		break;
		
		}
		
	}

}
