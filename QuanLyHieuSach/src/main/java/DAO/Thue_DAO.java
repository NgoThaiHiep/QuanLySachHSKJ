package DAO;

import java.util.ArrayList;

import entity.Thue;

public interface Thue_DAO {
	public ArrayList<Thue> layDuLieuThue();
	public boolean updateThue(Thue thue);
	public boolean insertThue(Thue thue);
	public boolean deleteThue(String id);

}
