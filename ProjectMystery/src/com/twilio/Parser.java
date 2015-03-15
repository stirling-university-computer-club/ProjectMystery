package com.twilio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Parser {

	public String testCases(ArrayList<Area> areas, Area area, Action action,
			boolean isitemect, Game gref) {
		ArrayList<Case> aCases = action.getCases();
		boolean works = true;

		/**
		 * If there are no cases to the action, print the output.
		 */
		if (aCases.size() == 0)
			return action.getOutput();
		else {

			for (int i = 0; i < aCases.size(); i++) { // FOR EACH CASE
				try {
					works = aCases.get(i).doesItWork(areas, area, gref);
				} catch (Exception e) {
					works = false;
				}
				if (works) {

					String[] effect = aCases.get(i).getEffects();
					String[] effectValues = aCases.get(i).getEffectValues();
					String output = aCases.get(i).getOutput();

					if (!effect[0].equals("")) {
						for (int m = 0; m < effect.length; m++) {

							try {
								aCases.get(i).applyEffect(areas, area, m,
										effectValues[m], gref);
							} catch (Exception e) {
								e.printStackTrace();
							}

						}
					}
					return output;
				} else {
					// If the loop ends and a case doesn't work, fail the
					// action.
					if (i == aCases.size() - 1) {
						return "No attributes fit the case";
					}
				}
			}

		}
		return "Fail #1"; // This will never happen
	}

	public String logicCheck(Game gref, String c, ArrayList<Area> areas){

		/**
		 * This is an insane amount of busywork. So what we have here is making
		 * references so we can bum the ids. I don't know if we actually need
		 * these. The way this works is we dig down to the bottom: AREA -> ROOM
		 * -> itemECT -> ACTION -> CASE The cases have the variables we need, so
		 * we have to get assign them after digging down. We then have to test
		 * the cases and if they work, then we output and effect.
		 */
		Area area = areas.get(gref.getPlayerArea());
		Item item = area.getItem(c);
		int itemID = item.getID();

		/**
		 * If the command does not reference an item, check if the command
		 * exists in the room.
		 */
		if (itemID == -1) {
			boolean isRoomAction = area.checkForAction(c);
			if (!isRoomAction) {
				return "Invalid area action.";
			}

			/**
			 * Test the data using the method above, and apply whatever effects
			 * if it works.
			 */
			Action action = area.getAction(c, true);
			return testCases(areas, area, action, false, gref);
		}

		/**
		 * If the command references an existing item, get the action of that
		 * item, and apply whatever works.
		 */
		Action action = item.getAction(c, false);
		if (!(action == null))
			return testCases(areas, area, action, true, gref);
		else {
			return "Invalid action referencing item.";
		}
	}

	public boolean isQuitting(String x) {
		if (x.equals("") || x.equals("bye") || x.equals("nothing"))
			return true;
		return false;
	}

}
