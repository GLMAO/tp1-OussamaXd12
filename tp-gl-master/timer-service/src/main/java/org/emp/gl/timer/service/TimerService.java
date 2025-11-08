/
package org.emp.gl.timer.service;

/**
 *
 * @author tina
 */
public interface TimerService extends TimeChangeProvider {

    int getMinutes();

    int getHeures();

    int getSecondes();

    int getDixiemeDeSeconde();

}
