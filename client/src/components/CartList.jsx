import React, { useEffect, useState } from "react";
import { getCartItemsByUserId, getItemsByIds } from "../services/api";
import { Grid } from "@mui/material";
import Cart from "./Cart";

const CartList = () => {
  const [cartItems, setCartItems] = useState([]);
  const [detailedItems, setDetailedItems] = useState([]);
  const userId = JSON.parse(localStorage.getItem("authenticatedUser"))?.userId;

  useEffect(() => {
    const fetchCartItems = async () => {
      try {
        if (userId) {
          const cartData = await getCartItemsByUserId(userId);
          console.log("cartData:", cartData);
          setCartItems(cartData);
          const itemIds = cartData.map((item) => item.itemId);
          console.log("itemIds:", itemIds);

          const detailedItemsData = await getItemsByIds(itemIds);
          console.log("Detailed items:", detailedItemsData);
          setDetailedItems(detailedItemsData);
        }
      } catch (error) {
        console.error("Error fetching cart items:", error);
      }
    };
    fetchCartItems();
  }, [userId]);

  return (
    <div>
      {detailedItems.map((item, index) => (
        <Grid item key={index} xs={12} sm={6} md={3}>
          <Cart item={item} />
        </Grid>
      ))}
    </div>
  );
};

export default CartList;
