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

    return response;
  } catch (error) {
    console.error("Error logging in:", error);
    throw error;
  }
};

export const getItems = async () => {
  try {
    const response = await api.get("/items");
    return response.data;
  } catch (error) {
    console.error("Error fetching items:", error);
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

export const getItemsByIds = async (itemIds) => {
  try {
    const response = await api.get("/items/details", {
      params: {
        itemIds: itemIds.join(","),
      },
    });
    return response.data;
  } catch (error) {
    console.error("Error fetching items by IDs:", error);
    throw error;
  }
};

export const addItem = async (itemData) => {
  try {
    const response = await api.post("/items", itemData);
    return response.data;
  } catch (error) {
    console.error("Error adding item:", error);
    throw error;
  }
};

export const addItemToCart = async (itemData) => {
  try {
    const response = await api.post("/cart/add", itemData);
    return response.data;
  } catch (error) {
    console.error("Error adding item to cart:", error);
    throw error;
  }
};

export const removeItemFromCart = async (userId, productId) => {
  try {
    const response = await api.delete(`/cart/remove/${userId}/${productId}`);
    return response.data;
  } catch (error) {
    console.error("Error removing item from cart:", error);
    throw error;
  }
};

export const getCartItemsByUserId = async (userId) => {
  try {
    const response = await api.get(`/cart/items/${userId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching cart items by user ID:", error);
    throw error;
  }
};
