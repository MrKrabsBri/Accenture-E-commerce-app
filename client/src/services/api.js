import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true,
});

export const registerUser = async (userData) => {
  try {
    const response = await api.post("/users", {
      username: userData.username,
      password: userData.password,
      userType: "USER",
      email: userData.email,
    });

    return response.data;
  } catch (error) {
    console.error("Error registering user:", error);
    throw error;
  }
};

export const loginUser = async (loginData) => {
  try {
    const response = await api.post("/users/verify-password", {
      username: loginData.username,
      password: loginData.password,
    });

    return response.data;
  } catch (error) {
    console.error("Error logging in:", error);
    throw error;
  }
};

export const deleteItem = async (itemId) => {
  try {
    const response = await api.delete(`/items/${itemId}`);
    return response.data;
  } catch (error) {
    console.error("Error deleting item:", error);
    throw error;
  }
};
