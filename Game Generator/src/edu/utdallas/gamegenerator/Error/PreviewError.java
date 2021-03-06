package edu.utdallas.gamegenerator.Error;

public abstract class PreviewError {
	public static enum Severity { LOW, MEDIUM, HIGH };
	public static enum Level { GAME, ACT, SCENE, SCREEN }
	private String message;
	private Severity severity;
	private Level level;
	
	public PreviewError(Level l, Severity s, String msg)
	{
		level = l;
		severity = s;
		message = msg;
	}
	
	public PreviewError() {}
	public final Severity getSeverity() { return severity; }
	public final Level getLevel() { return level; }
	public final String getMessage() { return message; }
	public abstract void fixError();
	public String toString() { return message; }
}
