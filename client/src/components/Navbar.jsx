import React, { useState, useEffect } from "react";
import {
  AppBar,
  Toolbar,
  Typography,
  Button,
  IconButton,
  Stack,
  Menu,
  MenuItem,
} from "@mui/material";
import { Link } from "react-router-dom";
import MenuIcon from "@mui/icons-material/Menu";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";

const Navbar = () => {
  const [anchorEl, setAnchorEl] = useState(null);
  const [user, setUser] = useState(null);
  const isAuthenticated = !!user;

  useEffect(() => {
    const storedUser = localStorage.getItem("authenticatedUser");
    if (storedUser) {
      setUser(JSON.parse(storedUser));
    }
  }, []);

  const handleMenuOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
  };

  const handleLogout = () => {
    localStorage.removeItem("authenticatedUser");
    localStorage.removeItem("jwtToken");
    setUser(null);
    setAnchorEl(null);
  };

  const renderMenuItems = () => {
    return [
      user ? (
        <Stack key="auth-options">
          <MenuItem key="logout" onClick={handleLogout}>
            Logout
          </MenuItem>
          <MenuItem
            key="cart"
            onClick={handleMenuClose}
            component={Link}
            to="/cart"
            sx={{
              display: isAuthenticated ? { xs: "block", sm: "none" } : "none",
            }}
          >
            <ShoppingCartIcon />
          </MenuItem>
        </Stack>
      ) : (
        <Stack key="auth-options">
          <MenuItem
            key="login"
            onClick={handleMenuClose}
            component={Link}
            to="/login"
          >
            Login
          </MenuItem>
          <MenuItem
            key="register"
            onClick={handleMenuClose}
            component={Link}
            to="/register"
          >
            Register
          </MenuItem>
        </Stack>
      ),
    ];
  };

  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          <Link to="/" style={{ textDecoration: "none", color: "inherit" }}>
            Ecommerce App
          </Link>
        </Typography>

        {user ? (
          <Stack direction="row" spacing={2}>
            <Typography variant="body1">Welcome, {user.username}!</Typography>
            <Button
              color="inherit"
              onClick={handleLogout}
              sx={{ display: { xs: "none", sm: "block" } }}
            >
              Logout
            </Button>
            <IconButton
              size="small"
              edge="end"
              color="inherit"
              aria-label="shopping cart"
              component={Link}
              to="/cart"
              sx={{ display: { xs: "none", sm: "block" } }}
            >
              <ShoppingCartIcon />
            </IconButton>
          </Stack>
        ) : (
          <Stack direction="row" spacing={2}>
            <Button
              color="inherit"
              component={Link}
              to="/login"
              sx={{ display: { xs: "none", sm: "block" } }}
            >
              Login
            </Button>
            <Button
              color="inherit"
              component={Link}
              to="/register"
              sx={{ display: { xs: "none", sm: "block" } }}
            >
              Register
            </Button>
            <IconButton
              size="small"
              edge="end"
              color="inherit"
              aria-label="shopping cart"
              component={Link}
              to="/cart"
              sx={{
                display: isAuthenticated ? { xs: "none", sm: "block" } : "none",
              }}
            >
              <ShoppingCartIcon />
            </IconButton>
          </Stack>
        )}

        <IconButton
          size="large"
          edge="end"
          color="inherit"
          aria-label="menu"
          onClick={handleMenuOpen}
          sx={{ display: { xs: "block", sm: "none" } }}
        >
          <MenuIcon />
        </IconButton>
        <Menu
          id="responsive-menu"
          anchorEl={anchorEl}
          open={Boolean(anchorEl)}
          onClose={handleMenuClose}
        >
          {renderMenuItems()}
        </Menu>
      </Toolbar>
    </AppBar>
  );
};

export default Navbar;
