import { FormControl, InputLabel, MenuItem, Paper, Select, styled, Table, TableBody, TableCell, tableCellClasses, TableContainer, TableHead, TableRow } from "@mui/material";
import React, { useState } from "react";
import { Route, Routes } from "react-router-dom";
const accountStatus = [
    { status: 'PENDING_VERIFICATION', title: 'Pending Verification', description: "pending..." },
    { status: 'ACTIVE', title: 'Active', description: 'Account is active' },
    { status: 'SUSPENDED', title: 'Suspended', description: 'Account is temporarily suspended!' },
    { status: 'DEACTIVATED', title: 'Deactivated', description: 'Account is deactivated' },
    { status: 'BANNED', title: 'Banned', description: 'Account is permanently banned' },
    { status: 'CLOSED', title: 'Closed', description: 'Account is permanently closed,' }
]

const Coupon = () => {
    const [accountstatus, setAccountStatus] = useState("ACTIVE");
    const handleChange = (event: any) => {
        setAccountStatus(event.target.value)
    }
    const StyledTableCell = styled(TableCell)(({ theme }) => ({
        [`&.${tableCellClasses.head}`]: {
          backgroundColor: theme.palette.common.black,
          color: theme.palette.common.white,
        },
        [`&.${tableCellClasses.body}`]: {
          fontSize: 14,
        },
      }));
      
      const StyledTableRow = styled(TableRow)(({ theme }) => ({
        '&:nth-of-type(odd)': {
          backgroundColor: theme.palette.action.hover,
        },
        // hide last border
        '&:last-child td, &:last-child th': {
          border: 0,
        },
      }));
      
      function createData(
        name: string,
        calories: number,
        fat: number,
        carbs: number,
        protein: number,
      ) {
        return { name, calories, fat, carbs, protein };
      }
      
      const rows = [
        createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
        createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
        createData('Eclair', 262, 16.0, 24, 6.0),
        createData('Cupcake', 305, 3.7, 67, 4.3),
        createData('Gingerbread', 356, 16.0, 49, 3.9),
      ];

    return (
        <div >

            
            <div>
                <TableContainer component={Paper}>
                    <Table sx={{ minWidth: 700 }} aria-label="customized table">
                        <TableHead>
                            <TableRow>
                                <StyledTableCell>Coupon Code</StyledTableCell>
                                <StyledTableCell>Start Date</StyledTableCell>
                                <StyledTableCell align="right">End Date</StyledTableCell>
                                <StyledTableCell align="right">Min Value Order</StyledTableCell>
                                <StyledTableCell align="right">Discount </StyledTableCell>
                                <StyledTableCell align="right">Status </StyledTableCell>
                                <StyledTableCell align="right">Delete </StyledTableCell>


                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {rows.map((row) => (
                                <StyledTableRow key={row.name}>
                                    <StyledTableCell component="th" scope="row">
                                        {row.name}
                                    </StyledTableCell>
                                    <StyledTableCell align="right">{row.calories}</StyledTableCell>
                                    <StyledTableCell align="right">{row.fat}</StyledTableCell>
                                    <StyledTableCell align="right">{row.carbs}</StyledTableCell>
                                    <StyledTableCell align="right">{row.protein}</StyledTableCell>
                                    <StyledTableCell align="right">{row.protein}</StyledTableCell>
                                    <StyledTableCell align="right">{row.protein}</StyledTableCell>


                                </StyledTableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
        </div>

    )
}

export default Coupon