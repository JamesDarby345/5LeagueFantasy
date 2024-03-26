import React, { useState } from "react";
import { MDBRow, MDBBtn, MDBCol, MDBIcon } from 'mdb-react-ui-kit';

function PageableList(props) {
	const children = props.children || []
	const PAGE_SIZE = 10;
	const [page, setPage] = useState(1);
	const getTotalPageCount = () => {
		return Math.ceil(children.length / PAGE_SIZE);
	}


	const renderPageList = () => {
		const arr = [];
		for (let i = 1; i <= getTotalPageCount(); ++i) {
			arr.push(
				<option value={i} key={i}>{i}</option>
			)
		}
		return arr;
	}

	const getNumItemsOnPage = () => {
		if (page < getTotalPageCount()) {
			return Math.min(children.length, PAGE_SIZE);
		} else {
			return children.length % PAGE_SIZE || PAGE_SIZE;
		}
	}

	const handlePageChange = (e) => {
		setPage(e.target.value);
	}

	const handlePageUp = () => {
		if (page < getTotalPageCount()) {
			setPage(page + 1);
		}
	}


	const handlePageDown = () => {
		if (page > 1) {
			setPage(page - 1);
		}
	}

	return (
		<>
			{children.length > 0 &&
				<MDBRow>
					<MDBCol className="col-md-8">Showing {getNumItemsOnPage()} out of {children.length} {props.itemType}</MDBCol>
					<MDBCol className="col-md-4 paginationControl">
						<MDBBtn floating size="sm" onClick={handlePageDown}>
							<MDBIcon icon="caret-left" />
						</MDBBtn>
						Page


						<select className="pageSelect" id="pageSelector" value={page} onChange={handlePageChange}>
							{renderPageList()}
						</select>
						of {getTotalPageCount()}
						<MDBBtn floating size="sm" onClick={handlePageUp}>
							<MDBIcon icon="caret-right" />
						</MDBBtn>
					</MDBCol>
				</MDBRow>
			}
			{children.filter((val, idx) => {return idx >= (page - 1 )* PAGE_SIZE && idx < page * PAGE_SIZE})}
			<style>
				{
					`
					.paginationControl {
						display: flex;
						justify-content: flex-end;
						gap: 7px;
					  }
					`
				}
			</style>
		</>
	)
}

export default PageableList;