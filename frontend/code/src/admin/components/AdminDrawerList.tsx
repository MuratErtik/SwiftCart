import React from "react";
import DrawerList from "../../seller/components/DrawerList.tsx";
import AccountBoxIcon from '@mui/icons-material/AccountBox';
import LogoutIcon from '@mui/icons-material/Logout';
import LocalOfferIcon from '@mui/icons-material/LocalOffer';
import CategoryIcon from '@mui/icons-material/Category';
import ElectricBoltIcon from '@mui/icons-material/ElectricBolt';
import HomeIcon from '@mui/icons-material/Home';
import IntegrationInstructionsIcon from '@mui/icons-material/IntegrationInstructions';
import DashboardIcon from '@mui/icons-material/Dashboard';
import AddIcon from '@mui/icons-material/Add';

const menu = [
    {
        name: "Dashboard",
        path: "/admin",
        icon: <DashboardIcon className="text-primary-color" />,
        activeIcon: <DashboardIcon className="text-white" />

    },
    
    {
        name: "Coupon",
        path: "/admin/coupon",
        icon: <IntegrationInstructionsIcon className="text-primary-color" />,
        activeIcon: <IntegrationInstructionsIcon className="text-white" />

    },
    {
        name: "Add New Coupon",
        path: "/admin/add-coupon",
        icon: <AddIcon className="text-primary-color" />,
        activeIcon: <AddIcon className="text-white" />

    },
    {
        name: "Home Page",
        path: "/admin/home-grid",
        icon: <HomeIcon className="text-primary-color" />,
        activeIcon: <HomeIcon className="text-white" />

    },
    {
        name: "Electronics Category",
        path: "/admin/electronics-category",
        icon: <ElectricBoltIcon className="text-primary-color" />,
        activeIcon: <ElectricBoltIcon className="text-white" />

    },
    {
        name: "Shop By Category",
        path: "/admin/shop-by-category",
        icon: <CategoryIcon className="text-primary-color" />,
        activeIcon: <CategoryIcon className="text-white" />

    },
    {
        name: "Deals",
        path: "/admin/deals",
        icon: <LocalOfferIcon className="text-primary-color" />,
        activeIcon: <LocalOfferIcon className="text-white" />

    }

]
const menu2 = [
    {
        name: "Account",
        path: "/admin/account",
        icon: <AccountBoxIcon className="text-primary-color" />,
        activeIcon: <AccountBoxIcon className="text-white" />

    },
    {
        name: "Logout",
        path: "/",
        icon: <LogoutIcon className="text-primary-color" />,
        activeIcon: <LogoutIcon className="text-white" />

    }

]
const AdminDrawerList = ({toggleDrawer}:{toggleDrawer:any}) => {
    return (
        <DrawerList menu={menu} menu2={menu2} toggleDrawer={toggleDrawer} />
    );
};


export default AdminDrawerList