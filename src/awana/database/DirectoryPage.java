package awana.database;

import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Renlar <liddev.com>
 */
public class DirectoryPage extends javax.swing.JFrame implements ListDataListener {

	private DatabaseWrapper databaseWrapper;
	private Record selectedRecord;
	private DefaultListModel<Listing> masterListModel;
	private DefaultListModel<Listing> searchListModel;

	/**
	 * Creates new form DirectoryPage
	 *
	 * @param databaseWrapper the connection to the database;
	 */
	public DirectoryPage(DatabaseWrapper databaseWrapper) {
		this.databaseWrapper = databaseWrapper;
		initComponents();
		searchListModel = masterListModel;
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
	 * content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        recordData = new javax.swing.JTabbedPane();
        recordScrollPane = new javax.swing.JScrollPane();
        recordItemList = new javax.swing.JList();
        newRecord = new javax.swing.JButton();
        deleteRecord = new javax.swing.JButton();
        searchBox = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Awana Database");

        recordScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        recordScrollPane.setName(""); // NOI18N

        recordItemList.setModel(getListModel());
        recordItemList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        recordItemList.setName(""); // NOI18N
        recordItemList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listingSelected(evt);
            }
        });
        recordItemList.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                listingSelected(evt);
            }
        });
        recordScrollPane.setViewportView(recordItemList);

        newRecord.setText("New");
        newRecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newRecordHandler(evt);
            }
        });

        deleteRecord.setText("Delete");
        deleteRecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteRecordHandler(evt);
            }
        });

        searchBox.setForeground(new java.awt.Color(150, 150, 150));
        searchBox.setText("Search");
        searchBox.setName(""); // NOI18N
        searchBox.setPreferredSize(new java.awt.Dimension(200, 25));
        searchBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchBoxFocusGainedHandler(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchBoxFocusLostHandler(evt);
            }
        });
        searchBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBoxKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(newRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recordScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(recordData, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(recordData)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recordScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(newRecord)
                            .addComponent(deleteRecord))))
                .addContainerGap())
        );

        recordScrollPane.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newRecordHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newRecordHandler
		saveCurrentRecord();
		newRecord();
		updateRecordData();
    }//GEN-LAST:event_newRecordHandler

    private void deleteRecordHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteRecordHandler
		Listing delete = getSelectedListing();

		String msgNoRecordSelected = "No Record Selected.";
		String[] confirmDeleteOptions = {"Delete", "Cancel"};
		String msgConfirmDelete = "<html>Are You sure you want to delete,</html>\n<html>"
				+ delete.getFullNameLastFirst()
				+ "</html>.\n<html><b>This can not be undone.</b></html>";

		if (delete == null) {
			JOptionPane.showMessageDialog(this, msgNoRecordSelected,
					"Null",
					JOptionPane.YES_NO_OPTION);
			return;
		}

		int choice = JOptionPane.showOptionDialog(this, msgConfirmDelete,
				"Confirm Delete",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				confirmDeleteOptions,
				confirmDeleteOptions[1]);
		if (choice == JOptionPane.YES_OPTION) {
			databaseWrapper.deleteListing(delete);
			removeListing(delete);
		}
		clearRecordData();
    }//GEN-LAST:event_deleteRecordHandler

    private void searchBoxFocusGainedHandler(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBoxFocusGainedHandler
		searchBox.setForeground(java.awt.Color.BLACK);

		if (searchBox.getText().equals("Search")) {
			searchBox.setText("");
		}
    }//GEN-LAST:event_searchBoxFocusGainedHandler

    private void searchBoxFocusLostHandler(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBoxFocusLostHandler
		searchBox.setForeground(Color.GRAY);

		if (searchBox.getText() == null || searchBox.getText().isEmpty()) {
			searchBox.setText("Search");
		}
    }//GEN-LAST:event_searchBoxFocusLostHandler

    private void listingSelected(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listingSelected
		if (recordItemList.getSelectedValue() != null) {
			Listing newSelection = (Listing) recordItemList.getSelectedValue();
			if (selectedRecord == null || selectedRecord.getID() != newSelection.getID()) {
				saveCurrentRecord();
				updateRecordData();
			}
		}
    }//GEN-LAST:event_listingSelected

    private void searchBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBoxKeyReleased
		String s = searchBox.getText();
		char pressed = evt.getKeyChar();
		if (s.equalsIgnoreCase(null) || s.isEmpty()) {
			searchListModel = masterListModel;
		} else if (pressed == '\b' || pressed == (char) 127) {
			searchListModel = searchRecords(masterListModel, s);
		} else {
			searchListModel = searchRecords(searchListModel, s);
		}
		recordItemList.setModel(searchListModel);
    }//GEN-LAST:event_searchBoxKeyReleased

	public void saveCurrentRecord() {
		if (selectedRecord != null) {
			databaseWrapper.saveRecord(selectedRecord);
			updateListings();
		}
	}

	public void updateListings() {
		int index = masterListModel.indexOf(selectedRecord.createListing());
		if(nameChanged()){
			masterListModel.remove(index);
			addListing(selectedRecord.createListing());
		}
	}

	public boolean nameChanged(){
		int index = masterListModel.indexOf(selectedRecord.createListing());
		Listing l = masterListModel.get(index);
		if(!stringsEqual(l.getFirstName(), selectedRecord.get("First Name").getData())){
			return true;
		}else if(!stringsEqual(l.getLastName(), selectedRecord.get("Last Name").getData())){
			return true;
		}
		return false;
	}

	public boolean stringsEqual(String a, String b){
		if(b == null && a == null){
			return true;
		}else if(b == null || a == null){
			return false;
		}else{
			return a.equals(b);
		}
	}

	public void updateRecordData() {
		clearRecordData();
		selectedRecord = databaseWrapper.getRecord(((Listing) recordItemList.getSelectedValue()).getID());
		selectedRecord.draw(recordData);
	}

	public void clearRecordData() {
		selectedRecord = null;
		recordData.removeAll();
	}

	public void removeListing(Listing r) {
		masterListModel.removeElement(r);
	}

	public void newRecord() {
		Record s = databaseWrapper.newRecord();
		Listing l = s.createListing();
		addListing(l);
		selectListing(l);
	}

	public void addListing(Listing listing) {
		int insertLocation = getInsertLocation(listing);
		masterListModel.insertElementAt(listing, insertLocation);
		searchListModel = masterListModel;
	}

	public int getInsertLocation(Listing listing) {
		int loc = 0;
		boolean notFound = true;
		if (masterListModel.size() > 0) {
			loc = (masterListModel.size() - 1) / 2;
			int increment = loc;
			while (notFound) {
				increment /= 2;
				if (increment == 0) {
					increment++;
				}

				int compair = masterListModel.get(loc).compairName(listing);
				int compairBelow = -1;

				if (loc > 0 && loc < masterListModel.size()) {
					compairBelow = masterListModel.get(loc - 1).compairName(listing);
				}
				if (compair == 0) {
					notFound = false;
				} else if (compair == 1 && compairBelow == -1) {
					notFound = false;
				} else if (compair == 1) {
					loc -= increment;
				} else if (compair == -1) {
					loc += increment;
				}
				if (loc == masterListModel.size()) {
					notFound = false;
				}
			}
		}
		return loc;
	}

	private void SortRecordsAlphabeticlyQuickSort(DefaultListModel<Listing> list) {
		if (!list.isEmpty()) {
			quickSortAlphabeticly(list, 0, list.size() - 1);
		}
	}

	private void quickSortAlphabeticly(DefaultListModel<Listing> list, int left, int right) {
		int index = quickSortPartition(list, left, right);
		if (left < index - 1) {
			quickSortAlphabeticly(list, left, index - 1);
		}
		if (index < right) {
			quickSortAlphabeticly(list, index, right);
		}
	}

	private int quickSortPartition(DefaultListModel<Listing> list, int left, int right) {
		int i = left, j = right;
		Listing tmp1, tmp2;
		Listing pivot = list.get((left + right) / 2);

		while (i <= j) {
			while (list.get(i).compairName(pivot) == -1) {
				i++;
			}
			while (list.get(j).compairName(pivot) == 1) {
				j--;
			}
			if (i <= j) {
				tmp1 = list.get(i);
				tmp2 = list.get(j);
				list.remove(i);
				list.add(i, tmp2);
				list.remove(j);
				list.add(j, tmp1);
				i++;
				j--;
			}
		}

		return i;
	}

	public void selectListing(Listing l) {
		recordItemList.setSelectedValue(l, true);
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteRecord;
    private javax.swing.JButton newRecord;
    private javax.swing.JTabbedPane recordData;
    private javax.swing.JList recordItemList;
    private javax.swing.JScrollPane recordScrollPane;
    private javax.swing.JTextField searchBox;
    // End of variables declaration//GEN-END:variables

	private ListModel getListModel() {
		masterListModel = databaseWrapper.getRecordListingsAsDefaultListModel();
		masterListModel.addListDataListener(this);
		SortRecordsAlphabeticlyQuickSort(masterListModel);
		return masterListModel;
	}

	private Listing getSelectedListing() {
		return (Listing) recordItemList.getSelectedValue();
	}

	private DefaultListModel<Listing> searchRecords(DefaultListModel<Listing> searchSet, String text) {
		DefaultListModel<Listing> resultSet = new DefaultListModel<Listing>();
		if (searchSet.isEmpty()) {
			return resultSet;
		}
		int counter = 0;
		while (counter < searchSet.size()) {
			Listing testee = searchSet.get(counter);
			if (testee.getFirstName() != null && testee.getLastName() != null && (testee.getLastName().contains(text) || testee.getFirstName().contains(text))) {
				resultSet.addElement(testee);
			}
			counter++;
		}
		return resultSet;
	}
	//TODO: put searching entries and loading data in seperate threads from application to eliminate temperary locking of application.

	@Override
	public void intervalAdded(ListDataEvent e) {
		//do-Nothing
	}

	@Override
	public void intervalRemoved(ListDataEvent e) {
		//do-Nothing
	}

	@Override
	public void contentsChanged(ListDataEvent e) {
		//do-Nothing
	}
}