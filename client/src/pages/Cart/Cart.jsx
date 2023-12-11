import React, { useEffect, useState } from "react";
import { getCartItemsByUserId, getItemsByIds } from "../../services/api";
import Navbar from "../../components/Navbar";
import CartList from "../../components/CartList";
import {
  Typography,
  Container,
  Box,
  Button,
  CircularProgress,
} from "@mui/material";
import CartFooter from "../../components/CartFooter";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { useNavigate } from "react-router-dom";
import EmptyCart from "../../assets/empty-cart.svg";

const CartPage = () => {
  const [cartItems, setCartItems] = useState([]);
  const [detailedItems, setDetailedItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const userId = JSON.parse(localStorage.getItem("authenticatedUser"))?.userId;

  useEffect(() => {
    const fetchCartItems = async () => {
      try {
        if (userId) {
          const cartData = await getCartItemsByUserId(userId);
          setCartItems(cartData);
          const itemIds = cartData.map((item) => item.itemId);
          const detailedItemsData = await getItemsByIds(itemIds);
          setDetailedItems(detailedItemsData);
        }
      } catch (error) {
        console.error("Error fetching cart items:", error);
      } finally {
        setLoading(false);
      }
    };
    fetchCartItems();
  }, [userId]);

  const removeFromCart = (itemIdToRemove) => {
    const updatedCartItems = cartItems.filter(
      (item) => item.itemId !== itemIdToRemove
    );
    const updatedItemIds = updatedCartItems.map((item) => item.itemId);
    const updatedDetailedItems = detailedItems.filter((item) =>
      updatedItemIds.includes(item.itemId)
    );

    setCartItems(updatedCartItems);
    setDetailedItems(updatedDetailedItems);
  };

  const navigate = useNavigate();

  const handleAddMoreItems = () => {
    navigate("/");
  };

  return (
    <div>
      <Navbar />
      <Container>
        <Typography variant="h4" align="center" pt={3}>
          Your cart
        </Typography>
        {loading ? (
          <Box display="flex" justifyContent="center" mt={3}>
            <CircularProgress />
          </Box>
        ) : detailedItems.length === 0 ? (
          <Box textAlign="center" mt={3}>
            <img src={EmptyCart} alt="Empty Cart" width={200} />
            <br />
            <Typography variant="h5">Cart is empty</Typography>
            <br />
            <Button
              variant="contained"
              color="success"
              startIcon={<ShoppingCartIcon />}
              onClick={handleAddMoreItems}
              mt={2}
            >
              Add items to cart
            </Button>
          </Box>
        ) : (
          <>
            <CartList
              detailedItems={detailedItems}
              cartData={cartItems}
              removeFromCart={removeFromCart}
            />
            <CartFooter detailedItems={detailedItems} cartItems={cartItems} />
          </>
        )}
      </Container>
    </div>
  );
};

export default CartPage;
