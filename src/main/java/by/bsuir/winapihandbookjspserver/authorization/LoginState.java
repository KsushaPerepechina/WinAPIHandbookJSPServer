package by.bsuir.winapihandbookjspserver.authorization;

import by.bsuir.winapihandbookjspserver.ApplicationConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Перечисление для статуса авторизованности.
 */
public enum LoginState {
    NOT_LOGGED_IN,
    LOGGED_IN;

    /**
     * Получение статуса авторизованности.
     * @param request - запрос
     * @return loginState - статус авторизованности
     */
    public static LoginState getLoginState(HttpServletRequest request) {
        final HttpSession session = request.getSession();

        LoginState loginState = (LoginState) session.getAttribute(ApplicationConstants.SESSION_LOGIN_STATE);
        if (loginState == null) {
            loginState = NOT_LOGGED_IN;
        }

        return loginState;
    }

    /**
     * Установка статуса авторизованности.
     * @param request - запрос
     * @param loginState - статус авторизованности
     */
    public static void setLoginState(HttpServletRequest request, LoginState loginState) {
        final HttpSession session = request.getSession();
        session.setAttribute(ApplicationConstants.SESSION_LOGIN_STATE, loginState);
    }

    /**
     * Получение токена сессии.
     * @param request - запрос
     * @return токен
     */
    public static String getSessionToken(HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute(ApplicationConstants.SESSION_TOKEN);

        return token == null ? "" : token;
    }

    /**
     * Установка токена сессии.
     * @param request - запрос
     * @param token - токен
     */
    public static void setSessionToken(HttpServletRequest request, String token) {
        final HttpSession session = request.getSession();
        session.setAttribute(ApplicationConstants.SESSION_TOKEN, token);
    }
}
