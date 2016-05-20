// Copyright 2015 theaigames.com (developers@theaigames.com)

//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at

//        http://www.apache.org/licenses/LICENSE-2.0

//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//  
//    For the full copyright and license information, please view the LICENSE
//    file that was distributed with this source code.

package amang;
import amang.BotLogic.BotLogic;

import java.util.Scanner;

public class BotApp {
	private final Scanner scan;

    private Playboard mPlayboard;
    private BotLogic mBotLogic;

    public static int mBotId = 0;
    public int mCurrentRound = 0;

    public BotApp() {
		this.scan = new Scanner(System.in);
	}
    
    public void run() {
        mPlayboard = new Playboard(0, 0);
        mBotLogic = new BotLogic(mPlayboard);

        while(scan.hasNextLine()) {
            String line = scan.nextLine();

            if(line.length() == 0) {
                continue;
            }

            String[] parts = line.split(" ");
            
            if(parts[0].equals("settings")) {
                if (parts[1].equals("field_columns")) {
                    mPlayboard.setColumns(Integer.parseInt(parts[2]));
                }
                if (parts[1].equals("field_rows")) {
                    mPlayboard.setRows(Integer.parseInt(parts[2]));
                }
                if (parts[1].equals("your_botid")) {
                    mBotId = Integer.parseInt(parts[2]);
                    mBotLogic.decideStrategy(mBotId);
                }
            } else if(parts[0].equals("update")) { /* new field data */
                if (parts[2].equals("field")) {
                    String data = parts[3];
                    mPlayboard.parseFromString(data); /* Parse Playboard with data */
                }
                else if (parts[2].equals("round")) {
                    mCurrentRound = Integer.parseInt(parts[3]);
                    mBotLogic.updateRound(mCurrentRound);
                }
            } else if(parts[0].equals("action")) {
                if (parts[1].equals("move")) { /* move requested */
                    int column = mBotLogic.makeTurn();
                    System.out.println("place_disc " + column);
                }
            }
            else { 
                System.out.println("unknown command");
            }
        }
    }
}