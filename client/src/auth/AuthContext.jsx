import { createContext, useContext, useState } from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const storedUser = localStorage.getItem("authenticatedUser");
  const storedToken = localStorage.getItem("jwtToken");

  const [user, setUser] = useState(storedUser ? JSON.parse(storedUser) : null);
  const [token, setToken] = useState(storedToken || null);

  const login = (authenticatedUser, jwtToken) => {
    setUser(authenticatedUser);
    setToken(jwtToken);

    localStorage.setItem(
      "authenticatedUser",
      JSON.stringify(authenticatedUser)
    );
    localStorage.setItem("jwtToken", jwtToken);
  };

  const logout = () => {
    localStorage.removeItem("authenticatedUser");
    localStorage.removeItem("jwtToken");
    setUser(null);
    setToken(null);
  };

  return (
    <AuthContext.Provider value={{ user, token, login, logout, setUser }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
