package controller;

public interface IClient {
    void execute(ISafeCommand command);
    void execute(ISafeCommand command, String argument);
    void executeUnsafe(IUnsafeCommand command) throws Exception;
    void executeUnsafe(IUnsafeCommand command, String argument) throws Exception;
}
